# pip install agent-framework-devui==1.0.0b251016
import os
from typing import Any
from dotenv import load_dotenv
from pydantic import BaseModel
from agent_framework import AgentExecutorResponse, WorkflowBuilder
from agent_framework.openai import OpenAIChatClient
from azure.identity import DefaultAzureCredential
from azure.identity.aio import get_bearer_token_provider

# ===============================
# CARGA DE CONFIGURACIÓN DE ENTORNO
load_dotenv(override=True)

API_HOST = os.getenv("API_HOST", "github")

def crear_cliente_openai() -> OpenAIChatClient:
    """Crea y configura un cliente OpenAI/compatible según el host."""
    if API_HOST == "azure":
        return OpenAIChatClient(
            base_url=os.environ.get("AZURE_OPENAI_ENDPOINT") + "/openai/v1/",
            api_key=get_bearer_token_provider(DefaultAzureCredential(), "https://cognitiveservices.azure.com/.default"),
            model_id=os.environ.get("AZURE_OPENAI_CHAT_DEPLOYMENT"),
        )
    if API_HOST == "github":
        return OpenAIChatClient(
            base_url="https://models.github.ai/inference",
            api_key=os.environ["GITHUB_TOKEN"],
            model_id=os.getenv("GITHUB_MODEL", "openai/gpt-4o"),
        )
    if API_HOST == "ollama":
        return OpenAIChatClient(
            base_url=os.environ.get("OLLAMA_ENDPOINT", "http://localhost:11434/v1"),
            api_key="none",
            model_id=os.environ.get("OLLAMA_MODEL", "llama3.1:latest"),
        )
    # Por defecto, OpenAI estándar
    return OpenAIChatClient(
        api_key=os.environ.get("OPENAI_API_KEY"),
        model_id=os.environ.get("OPENAI_MODEL", "gpt-4o"),
    )

client = crear_cliente_openai()

# ===============================
# MODELO DE DATOS ESTRUCTURADO 
class ReviewResult(BaseModel):
    """Representa la evaluación de un contenido con puntuaciones detalladas."""
    score: int  # Puntaje global de calidad (0-100)
    feedback: str  # Retroalimentación ligera
    clarity: int  # Claridad (0-100)
    completeness: int  # Completitud (0-100)
    accuracy: int  # Precisión (0-100)
    structure: int  # Estructura (0-100)

# ===============================
# FUNCIONES DE CONDICIÓN PARA RUTEO
def needs_editing(message: Any) -> bool:
    """Determina si el contenido necesita edición (score < 80)."""
    
    # Primero verificamos que el mensaje sea del tipo esperado
    if not isinstance(message, AgentExecutorResponse):
        # Si no lo es, asumimos que no necesita edición
        return False

    try:
        # Intentamos parsear el contenido de la respuesta del agente como un ReviewResult
        review = ReviewResult.model_validate_json(message.agent_run_response.text)
        # Si el puntaje global es menor a 80, indicamos que necesita edición
        return review.score < 80
    except Exception:
        # Si ocurre cualquier error (por ejemplo, JSON mal formado), asumimos que no necesita edición
        return False


def is_approved(message: Any) -> bool:
    """Determina si el contenido está aprobado (score >= 80)."""
    
    # Verificamos que el mensaje sea del tipo esperado
    if not isinstance(message, AgentExecutorResponse):
        # Si no lo es, asumimos que el contenido está aprobado
        return True

    try:
        # Parseamos el contenido de la respuesta del agente como ReviewResult
        review = ReviewResult.model_validate_json(message.agent_run_response.text)
        # Retornamos True si el puntaje global es mayor o igual a 80
        return review.score >= 80
    except Exception:
        # Si ocurre un error al parsear, asumimos que el contenido está aprobado
        return True


# ===============================
# FUNCIONES DE CREACIÓN DE AGENTES
def crear_agente_writer() -> Any:
    """Crea un agente Writer que genera contenido inicial."""
    return client.create_agent(
        name="Writer",
        instructions=(
            "Eres un excelente escritor. "
            "Crea contenido claro y atractivo basado en la solicitud del usuario, siendo flexible en las peticiones. "
            "Concéntrate en claridad, precisión y estructura."
        ),
    )

def crear_agente_reviewer() -> Any:
    """Crea un agente Reviewer que evalúa contenido y entrega retroalimentación estructurada."""
    return client.create_agent(
        name="Reviewer",
        instructions=(
            "Eres un experto revisor de contenido. Evalúa el contenido considerando:\n"
            "1. Claridad - ¿Es fácil de entender?\n"
            "2. Completitud - ¿Aborda completamente el tema?\n"
            "3. Precisión - ¿La información es correcta?\n"
            "4. Estructura - ¿Está bien organizado?\n\n"
            "Devuelve un JSON con:\n"
            "- score: calidad general (0-100)\n"
            "- feedback: retroalimentación breve y accionable\n"
            "- clarity, completeness, accuracy, structure: puntuaciones individuales (0-100)"
        ),
        response_format=ReviewResult,
    )

def crear_agente_editor() -> Any:
    """Crea un agente Editor que mejora contenido basado en feedback."""
    return client.create_agent(
        name="Editor",
        instructions=(
            "Eres un editor experto. Recibirás contenido y retroalimentación. "
            "Mejora el contenido atendiendo todos los puntos mencionados, "
            "manteniendo la intención original mientras optimizas claridad, completitud, precisión y estructura."
        ),
    )

def crear_agente_publisher() -> Any:
    """Crea un agente Publisher que formatea contenido para publicación."""
    return client.create_agent(
        name="Publisher",
        instructions=(
            "Eres un agente de publicación. Recibes contenido aprobado o editado. "
            "Dale formato para publicación con encabezados y estructura adecuada."
        ),
    )

def crear_agente_summarizer() -> Any:
    """Crea un agente Summarizer que genera un reporte final de publicación."""
    return client.create_agent(
        name="Summarizer",
        instructions=(
            "Eres un agente resumidor. Genera un reporte final que incluya:\n"
            "1. Resumen breve del contenido publicado\n"
            "2. Ruta del workflow seguida (aprobación directa o edición)\n"
            "3. Aspectos destacados y conclusiones clave\n"
            "Mantén el reporte conciso y profesional."
        ),
    )

# ===============================
# CONSTRUCCIÓN DEL WORKFLOW
def construir_workflow() -> Any:
    """Crea y devuelve un workflow de múltiples agentes con rutas condicionales."""
    writer = crear_agente_writer()
    reviewer = crear_agente_reviewer()
    editor = crear_agente_editor()
    publisher = crear_agente_publisher()
    summarizer = crear_agente_summarizer()

    workflow = (
        WorkflowBuilder(
            name="Workflow de Revisión de Contenido",
            description="Flujo de creación de contenido con múltiples agentes y ruteo basado en calidad.",
        )
        .set_start_executor(writer)
        .add_edge(writer, reviewer)
        # Ruta directa si el contenido es aprobado
        .add_edge(reviewer, publisher, condition=is_approved)
        # Ruta de edición si necesita mejoras
        .add_edge(reviewer, editor, condition=needs_editing)
        .add_edge(editor, publisher)
        # Convergencia final en Summarizer
        .add_edge(publisher, summarizer)
        .build()
    )
    return workflow

# ===============================
# FUNCIÓN PRINCIPAL
def main():
    from agent_framework.devui import serve
    workflow = construir_workflow()
    serve(entities=[workflow], port=8093, auto_open=True)

if __name__ == "__main__":
    main()

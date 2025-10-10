import csv
import os
from pathlib import Path

import azure.identity
import openai
from dotenv import load_dotenv
from lunr import lunr

# Configuro el cliente de OpenAI para usar la API de Azure, OpenAI.com u Ollama
load_dotenv(override=True)
API_HOST = os.getenv("API_HOST", "github") #Uso el cliente de GitHub como predeterminado

#Condicionales para establecer la conexion en caso de cada Host
if API_HOST == "azure":
    token_provider = azure.identity.get_bearer_token_provider(
        azure.identity.DefaultAzureCredential(), "https://cognitiveservices.azure.com/.default"
    )
    client = openai.OpenAI(
        base_url=os.environ["AZURE_OPENAI_ENDPOINT"],
        api_key=token_provider,
    )
    MODEL_NAME = os.environ["AZURE_OPENAI_CHAT_DEPLOYMENT"]

elif API_HOST == "ollama":
    client = openai.OpenAI(base_url=os.environ["OLLAMA_ENDPOINT"], api_key="nokeyneeded")
    MODEL_NAME = os.environ["OLLAMA_MODEL"]

elif API_HOST == "github":
    client = openai.OpenAI(base_url="https://models.github.ai/inference", api_key=os.environ["GITHUB_TOKEN"])
    MODEL_NAME = os.getenv("GITHUB_MODEL", "openai/gpt-4o")

else:
    client = openai.OpenAI(api_key=os.environ["OPENAI_KEY"])
    MODEL_NAME = os.environ["OPENAI_MODEL"]

# Ruta del archivo CSV con los datos de autos híbridos a manejar
CSV_PATH = Path(__file__).with_name("hybridos.csv")

# Carga el contenido del CSV en memoria
with CSV_PATH.open(newline="", encoding="utf-8") as file:
    reader = csv.reader(file)
    rows = list(reader)  # Convierte el CSV a una lista con filas y columnas

# Crea una lista de documentos para indexar con Lunr
# Cada documento tiene un id=fila y un cuerpo de texto con todos los campos concatenados
documents = [{"id": (i + 1), "body": " ".join(row)} for i, row in enumerate(rows[1:])]

# Crea el índice de búsqueda local con Lunr
index = lunr(ref="id", fields=["body"], documents=documents)

def search(query):
    """
    Busca en el índice las filas que coinciden con la consulta del usuario
    y devuelve una tabla en formato Markdown con los resultados.
    """
    results = index.search(query)  # Ejecuta la búsqueda en Lunr
    matching_rows = [rows[int(result["ref"])] for result in results]  # Obtiene las filas originales

    # Construye una tabla en formato Markdown incluyendo encabezados + separadores + filas
    matches_table = " | ".join(rows[0]) + "\n"  # Encabezados
    matches_table += " | ".join(" --- " for _ in range(len(rows[0]))) + "\n"  # Línea divisoria
    matches_table += "\n".join(" | ".join(row) for row in matching_rows)  # Datos encontrados
    return matches_table

# Mensaje del sistema para reescribir preguntas en formato "keyword". Muy importante poner el formato y los detalles deseados
QUERY_REWRITE_SYSTEM_MESSAGE = """
Eres un asistente útil que reescribe preguntas de usuarios a consultas de alta calidad de tipo keyword
para un índice de filas CSV con estas columnas: vehicle, year, msrp, acceleration, mpg, class.
Das consultas de alta calidad de keyword no tienen puntuación y están en minúsculas.
Se te proporcionará la nueva pregunta del usuario y el historial de conversación.
Responde SÓLO con la consulta de keyword sugerida, sin texto adicional o extra.
"""

# Mensaje del sistema para responder preguntas basadas en los datos del CSV dado
SYSTEM_MESSAGE = """
Eres un asistente útil que responde preguntas sobre automóviles basándose en un conjunto de datos de coches híbridos.
Debes utilizar el conjunto de datos para responder las preguntas, no debes proporcionar información que no
esté en las fuentes proporcionadas en el archivo CSV.
"""

# Se inicializa el historial de mensajes con el mensaje del sistema
messages = [{"role": "system", "content": SYSTEM_MESSAGE}]

# Bucle principal infinito del chat
while True:
    # Se da espacio al usuario para escribir su pregunta
    question = input("\nTu pregunta sobre coches eléctricos: ")

    # Primero se reescribe la pregunta para mejorar la búsqueda
    response = client.chat.completions.create(
        model=MODEL_NAME,
        temperature=0.05,
        messages=[
            {"role": "system", "content": QUERY_REWRITE_SYSTEM_MESSAGE},
            {
                "role": "user",
                "content": f"Nueva pregunta del usuario:{question}\n\nHistorial de conversación:{messages}",
            },
        ],
    )

    # La consulta reescrita en formato keyword
    search_query = response.choices[0].message.content
    print(f"Consulta reescrita: {search_query}")

    # Se realiza la búsqueda en el CSV usando Lunr
    matches = search(search_query)
    print("Coincidencias encontradas:\n", matches)

    # Se prepara y se agrega el contexto con la pregunta y las coincidencias encontradas
    messages.append({"role": "user", "content": f"{question}\nFuentes: {matches}"})

    # Se genera la respuesta del modelo basada en las coincidencias encontradas
    response = client.chat.completions.create(
        model=MODEL_NAME,
        temperature=0.3,
        messages=messages
    )

    # Se obtiene la respuesta final del modelo
    bot_response = response.choices[0].message.content

    # Se agrega la respuesta al historial de conversación
    messages.append({"role": "assistant", "content": bot_response})

    # Se muestra la respuesta al usuario en la terminal
    print(f"\nRespuesta de {API_HOST} {MODEL_NAME}: \n")
    print(bot_response)

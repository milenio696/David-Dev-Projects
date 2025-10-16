import os
import azure.identity
import openai
from dotenv import load_dotenv

# region Configuración del Cliente OpenAI

# Cargo las variables de entorno desde el archivo .env que hay en el repositorio virtual.
# override=True permite sobrescribir variables ya definidas en el entorno.
load_dotenv(".env", override=True)

# Obtiene el host para usar como backend de OpenAI.
openai_host = os.getenv("OPENAI_HOST", "github") # Si no se define, usa "github" por defecto.

# --- Caso 1: GitHub Models
if openai_host == "github":
    print("Usando GitHub Models con GITHUB_TOKEN como clave")
    # Crea el cliente de OpenAI apuntando al endpoint de GitHub Models y envío el token de GitHub.
    openai_client = openai.OpenAI(
        api_key=os.environ["GITHUB_TOKEN"],
        base_url="https://models.github.ai/inference",
    )
    # Defino el modelo a usar, con un valor por defecto razonable.
    model_name = os.getenv("OPENAI_MODEL", "openai/gpt-4o")

# --- Caso 2: Servidor local
elif openai_host == "local":
    print("Usando API local compatible con OpenAI sin clave")
    # Usa un endpoint local que emule la API de OpenAI. FUnciona para pruebas offline.
    openai_client = openai.OpenAI(
        api_key="no-key-required",
        base_url=os.environ["LOCAL_OPENAI_ENDPOINT"]
    )
    model_name = os.getenv("OPENAI_MODEL", "gpt-4o")

# --- Caso 3: Azure
elif openai_host == "azure" and os.getenv("AZURE_OPENAI_KEY_FOR_CHATVISION"):
    # Autenticación usando una clave directa (no recomendado para producción).
    print("Usando Azure OpenAI con clave")
    openai_client = openai.OpenAI(
        base_url=os.environ["AZURE_OPENAI_ENDPOINT"] + "/openai/v1/",
        api_key=os.environ["AZURE_OPENAI_KEY_FOR_CHATVISION"],
    )
    # Nota importante: en Azure, este modelo suele ser el nombre del deployment, no el modelo real
    model_name = os.getenv("OPENAI_MODEL", "gpt-4o")

# --- CASO 4: Azure con credenciales de Azure CLI
elif openai_host == "azure" and os.getenv("AZURE_OPENAI_ENDPOINT"):
    tenant_id = os.environ["AZURE_TENANT_ID"]
    print("Usando Azure OpenAI con credencial de Azure Developer CLI")

    # Creo una credencial basada en la sesión activa de Azure CLI.
    default_credential = azure.identity.AzureDeveloperCliCredential(tenant_id=tenant_id)

    # Obtiene un proveedor de token válido para Azure Cognitive Services
    token_provider = azure.identity.get_bearer_token_provider(
        default_credential,
        "https://cognitiveservices.azure.com/.default"
    )

    # Crea el cliente OpenAI usando el token dinámico de Azure
    openai_client = openai.OpenAI(
        base_url=os.environ["AZURE_OPENAI_ENDPOINT"] + "/openai/v1/",
        api_key=token_provider,
    )

    # De nuevo, el modelo en Azure corresponde al deployment seteado
    model_name = os.getenv("OPENAI_MODEL", "gpt-4o")

# Finalmente, imprime qué modelo se está utilizando dependiendo del host utilizado.
print(f"Usando modelo {model_name}")
# endregion



# region Analisis de Imagenes

# Ejemplo de análisis de imágenes con GPT-4o Vision
# Envío un comando como user al AI, adjuntando un URL de una imagen que será analizada y traerá una respuesta.
messages = [
    {
        "role": "user",
        "content": [
            {"text": "¿Es un caballo?", "type": "text"},
            {
                "image_url": {"url": "https://upload.wikimedia.org/wikipedia/commons/6/6e/Ur-painting.jpg"},
                "type": "image_url",
            },
        ],
    }
]
#Responde con un chat completion usando el modelo definido y el mensaje con la imagen
response = openai_client.chat.completions.create(model=model_name, messages=messages)

print(response.choices[0].message.content) 

# endregion



# region Envío de imagen base64

import base64  # Módulo estándar de Python para codificar y decodificar datos en Base64.

# Esta función recibe la ruta de un archivo de imagen y lo convierte en una cadena
# codificada en Base64, que es un formato adecuado para enviar imágenes en JSON.
def open_image_as_base64(filename):

    # Abre el archivo de imagen en modo binario ('rb' = read binary) para que Python lea los bytes
    with open(filename, "rb") as image_file:
        image_data = image_file.read()  # Lee todos los bytes de la imagen entera

    # Codifica los bytes en Base64. El resultado es una secuencia de bytes codificados.
    image_base64 = base64.b64encode(image_data).decode("utf-8") # Con decode("utf-8"), se convierte a una cadena de texto regular

    # Devuelve la imagen en formato de URL, lista para usarse en un campo de tipo "image_url".
    return f"data:image/png;base64,{image_base64}"


# En este bloque, se forma un mensaje para enviar al modelo multimodal seleccionado.
response = openai_client.chat.completions.create(
    model=model_name, 
    messages=[
        {
            "role": "user",
            "content": [
                # Primer elemento: texto con la pregunta.
                {"text": "Estos son cocodrilos o caimanes? Necesito guía en la interpretación correcta.", "type": "text"}, 

                # Se convierte la imagen en Base64 mediante la función definida arriba.
                {"image_url": {"url": open_image_as_base64("../mystery_reptile.png")}, "type": "image_url"}, # La ruta debe tener un archivo de imagen válido.
            ],
        }
    ],
)

# Una vez que la API devuelve la respuesta, el objeto 'response' contiene la información.
print(response.choices[0].message.content)


# endregion



# region Escenario para análisis de imágenes

# Analisis de manú para personas con problemas de vista
response = openai_client.chat.completions.create(
    model=model_name,
    messages=[
        {
            "role": "user",
            "content": [
                {"text": "¿hay algo bueno para diabéticos en este menú?", "type": "text"},
                {"image_url": {"url": open_image_as_base64("../menu.png")}, "type": "image_url"}, # Imagen local no guardada en este repositorio
            ],
        }
    ],
)
print(response.choices[0].message.content)


# Subtítulos para imágenes
response = openai_client.chat.completions.create(
    model=model_name,
    messages=[
        {
            "role": "user",
            "content": [
                {"text": "Sugiere un texto alternativo para esta imagen", "type": "text"},
                {"image_url": {"url": open_image_as_base64("../azure_arch.png")}, "type": "image_url"},
            ],
        }
    ],
)
print(response.choices[0].message.content)


# Analisis de Graficos
messages = [
    {
        "role": "user",
        "content": [
            {"text": "¿En qué zona estamos perdiendo más árboles?", "type": "text"},
            {
                "image_url": {
                    "url": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/20210331_Global_tree_cover_loss_-_World_Resources_Institute.svg/1280px-20210331_Global_tree_cover_loss_-_World_Resources_Institute.svg.png"
                },
                "type": "image_url",
            },
        ],
    }
]
response = openai_client.chat.completions.create(model=model_name, messages=messages)

print(response.choices[0].message.content)



# Automatizacion de 2 las funciones
def open_image_as_base64(filename):
    with open(filename, "rb") as f:
        return f"data:image/png;base64,{base64.b64encode(f.read()).decode('utf-8')}"

# Lista de tareas automáticas
tareas = [
    {
        "texto": "Sugiere un texto alternativo para esta imagen",
        "imagen": open_image_as_base64("../azure_arch.png"),
    },
    {
        "texto": "¿En qué zona estamos perdiendo más árboles?",
        "imagen": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/20210331_Global_tree_cover_loss_-_World_Resources_Institute.svg/1280px-20210331_Global_tree_cover_loss_-_World_Resources_Institute.svg.png",
    },
]

# Ejecutar todas las tareas
for tarea in tareas:
    response = openai_client.chat.completions.create(
        model=model_name,
        messages=[
            {
                "role": "user",
                "content": [
                    {"type": "text", "text": tarea["texto"]},
                    {"type": "image_url", "image_url": {"url": tarea["imagen"]}},
                ],
            }
        ],
    )
    print(f"\n--- {tarea['texto']} ---")
    print(response.choices[0].message.content)


# endregion
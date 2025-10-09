import os

import azure.identity
import openai
from dotenv import load_dotenv

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

#Le asigno un comando y un proposito al modelo AI
messages = [
    {"role": "system", "content": "Soy un large language model inteligente y avanzado."},
]

while True:
    question = input("\nTu pregunta: ")
    print("Enviando pregunta...")

    #Agrego la consulta al arreglo messages para que el modelo logre recordar informacion
    messages.append({"role": "user", "content": question})
    response = client.chat.completions.create(
        model=MODEL_NAME,
        messages=messages,
        temperature=0.7, #Establece la creatividad del modelo
        stream=True, #Activa el metodo stream que permite una emision de los datos mas veloz
    )

    print("\nRespuesta: ")
    bot_response = ""
    #Corro el proceso para el stream y que las respuestas se vayan mostrando con forme se generan
    for event in response:
        if event.choices and event.choices[0].delta.content:
            content = event.choices[0].delta.content
            print(content, end="", flush=True)
            bot_response += content
    print("\n")
    #Agrego la respuesta a la variable messages con el rol que ayudara al sistema a acordarse de la respuesta
    messages.append({"role": "assistant", "content": bot_response}) 

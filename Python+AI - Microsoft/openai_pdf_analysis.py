import fitz  # PyMuPDF
from PIL import Image
import base64

# Función para convertir una imagen local a Base64 embebida
def open_image_as_base64(filename):
    """
    Recibe la ruta de un archivo de imagen y devuelve
    un string con la imagen codificada en Base64
    en formato data URI: data:image/png;base64,...
    """
    with open(filename, "rb") as f:
        return f"data:image/png;base64," + base64.b64encode(f.read()).decode("utf-8")


# -------------------------
# 1. Abrir PDF y convertir las páginas a imágenes

pdf_filename = "../plants.pdf" #archivo local no almacenado en el repositorio

# Abre el PDF solo una vez
doc = fitz.open(pdf_filename)

# Recorre las paginas indicadas, en este caso solo 3
for i in range(min(3, doc.page_count)):
    page = doc[i]  # cargar página i
    pix = page.get_pixmap()  # renderizar página a pixmap (imagen en memoria)
    # Crear imagen PIL desde los bytes del pixmap
    img = Image.frombytes("RGB", [pix.width, pix.height], pix.samples)
    # Guardar imagen temporalmente para luego convertir a Base64
    img.save(f"page_{i}.png")


# -------------------------
# 2. Preparar mensaje para OpenAI

user_content = [{"text": "¿Qué plantas se mencionan en estas páginas?", "type": "text"}]

# Adjunta las imágenes convertidas a Base64
for i in range(min(3, doc.page_count)):
    user_content.append({
        "type": "image_url",
        "image_url": {"url": open_image_as_base64(f"../page_{i}.png")}
    })


# -------------------------
# 3. Llamada al modelo multimodal

response = openai_client.chat.completions.create(
    model=model_name,
    messages=[{"role": "user", "content": user_content}]
)

print(response.choices[0].message.content)

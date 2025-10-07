# Importo la funcion create_app del paquete app
from app import create_app

# Instancio el constructor create_app que se encuentra en __init.py__
app = create_app()

if __name__ == '__main__':
    app.run(debug=True)
    

    
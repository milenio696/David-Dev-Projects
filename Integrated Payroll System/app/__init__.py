from flask import Flask

# Creo y configuro la instancia con Flask
def create_app():
    app = Flask(__name__)

    # Se usa un contexto de la aplicacion para hacer configs adicionales 
    with app.app_context():
        # Desde la raiz del directorio importamos a routes.py para que las rutas sean definidas y registradas en Flask
        from . import routes
        
    # Se retorna la instancia Flask ya configrada
    return app

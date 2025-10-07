# De la capas importamos una clase en especifico
from DataAccess.data_access import DataAccess
from Entities.usuarios import User

# Creo la clase de la capa
class BusinessLogic:

    # Metodo constructor
    def __init__(self): # Self hace referencia a la calse actual
        self.data_access = DataAccess() # Instancio la clase DataAccess
    

    #SCRIPT PROYECTO
    def login(self, user:User): # Recibe un usuario almacenado en la clase User que importamos de Entities
     if user.validate(): # Llama a la funcion de Entities
        resultado, contra = self.data_access.extraer_username(user.name, user.pwd)
        if user.name == resultado and user.pwd == contra:
            return 'Bienvenido, el login ha sido exitosamente autenticado!'
        else:
            return 'Contraseña o usuario incorrecto.'
     


     #usar login manager en flask para todas las rutas 



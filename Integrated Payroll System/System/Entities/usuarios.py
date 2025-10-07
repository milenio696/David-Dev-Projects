# Clase User que se encuentra en capa business
class User:

    # Metodo contructor
    def __init__(self, id=None, name=None, pwd=None):
        # Las variables que voy a recibir las almaceno en esta clase
        self.id = id
        self.name = name
        self.pwd = pwd

    # Metodo de validacion de usuario
    def validate(self):
        if not self.name or not self.pwd: # Si no hay nombre/contraseña levanta error
            raise ValueError('Por favor completa ambos campos solicitados.')
            return False
        return True
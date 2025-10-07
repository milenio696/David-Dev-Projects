# De la capas importamos una clase en especifico
from DataAccess.data_access import DataAccess

# Creo la clase de la capa
class BusinessLogic1:

    # Metodo constructor
    def __init__(self): # Self hace referencia a la calse actual
        self.data_access = DataAccess() # Instancio la clase DataAccess y la defino

    
    def procesar_seleccion_Empleado(self):
        empleados = self.data_access.extraer_empleados()
        return empleados #Retorno los empleados extraidos
    
    def extraer_info_empleado_seleccionado(self, id_empleado):
        #Recibo los valores asignados
        data = self.data_access.extraer_informacion_empleado(id_empleado)
        return data
    
    def editar_empleado(self, data):
        self.data_access.editar_info_empleado(data=data)

    def eliminar_empleado(self, id_empleado):
        self.data_access.eliminar_empleado(id_empleado)

    def crear_empleado(self, new_data):
        self.data_access.crear_empleado(new_data=new_data)
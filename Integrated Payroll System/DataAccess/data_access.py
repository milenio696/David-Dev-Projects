# Importamos modulo para la conexion con SQL
import pyodbc

class DataAccess:

    # Metodo contructor
    def __init__(self):
        # Ingreso la cadena de conexion con la BD
        self.connection_string = 'DRIVER={ODBC Driver 17 for SQL Server};SERVER=DESKTOP-5AUR71M\SQLEXPRESS;DATABASE=app_flask;Trusted_Connection=yes;'

              #  cursor.execute("INSERT INTO Usuarios (nombre) OUTPUT INSERTED.id VALUES (?)", (name,)) # El '?' envia la variable del parentesis a SQL


    
    #RECOLECTAR USERS PARA LOGIN
    def extraer_username(self, name, pwd):
        try: 
            # La funcion connect va a recibir la cadena de SQL para conectarse a la BD
            with pyodbc.connect(self.connection_string) as conex:
                cursor = conex.cursor()
                cursor.execute('SELECT nombre, contra FROM Usuarios WHERE nombre = ? AND contra = ?', (name,pwd,))
                
                resultado = cursor.fetchone()

                if resultado:
                    empleado = resultado[0]
                    contra = resultado[1]
                    conex.commit

                else:
                    return None, None
            
            
            return empleado, contra
        
        except Exception as e:
            raise Exception(f'Error al enviar el usuario: {e}')
        
    
    #RECOLECTO EN UN PAR DE LISTAS LOS NOMBRES Y APELLIDOS 
    def extraer_empleados(self):
        try:
            with pyodbc.connect(self.connection_string) as conex:
                cursor = conex.cursor()
                cursor.execute('SELECT id, nombre, apellido FROM Empleados')
                
                rows = cursor.fetchall()

                empleados = {}

                if rows:
                    for row in rows:

                        empleados[row[0]] = row[1]+ ' ' +row[2]

                    conex.commit
                
                else:
                    return None
            

            return empleados

        except Exception as e:

            raise Exception(f'No se ha podido extrar a los empleados de la base de datos: {e}')
    

    #EXTRAIGO LA INFORMACION COMPLETA DEL EMPLEADO SELECCIONADO
    def extraer_informacion_empleado(self, id_empleado):
        try:
            with pyodbc.connect(self.connection_string) as conex:
                cursor = conex.cursor()
                cursor.execute('SELECT nombre, apellido, correo, salario_Bruto, id FROM Empleados WHERE id = ?', (id_empleado,))
                
                result = cursor.fetchone()

                if result:
                    nombre=result[0]
                    apellido=result[1]
                    correo=result[2]
                    salario=result[3]
                    id=result[4]
                    

                    data = nombre, apellido, correo, salario, id
                    conex.commit
                else:
                    return None
            

            return data

        except Exception as e:
            raise Exception(f'No se ha podido procesar la extraccion de la informaion del empleado seleccionado: {e}')
        

    def editar_info_empleado(self, data):
        try:
            with pyodbc.connect(self.connection_string) as conex:
                cursor = conex.cursor()
                cursor.execute('UPDATE Empleados SET nombre=?, apellido=?, correo=?, salario_Bruto=? WHERE id=?', (data[1], data[2], data[3], data[4], data[0],))
                conex.commit()

        except Exception as e:
            raise Exception(f'No se ha podido procesar la edición del usuario: {e}')
        


    def eliminar_empleado(self, id_empleado):
        try:
            with pyodbc.connect(self.connection_string) as conex:
                cursor = conex.cursor()
                cursor.execute('DELETE FROM Empleados WHERE id=?', (id_empleado,))
                conex.commit()

        except Exception as e:
            raise Exception(f'No se ha podido procesar la eliminación del usuario: {e}')

        
    
    def crear_empleado(self, new_data):
        try:
            with pyodbc.connect(self.connection_string) as conex:
                cursor = conex.cursor()
                cursor.execute('INSERT INTO Empleados VALUES(?,?,?,?);', (new_data[0], new_data[1], new_data[2], new_data[3],))
                conex.commit()

        except Exception as e:
            raise Exception(f'No se ha podido procesar la creación del usuario: {e}')


# Importo las funciones y clases de Flask
from flask import request, render_template, flash, current_app as app
from datetime import datetime

#Importo la funcion zip
app.jinja_env.globals.update(zip=zip)

# Importo las clases necesarias
from Business.business_usuarios import BusinessLogic
from Business.business_empleados import BusinessLogic1
from Entities.usuarios import User
from Entities.empleados import Empleado
from Business.business_pago import business_logic_payment
from Business.business_factura import Facturacion_de_pago
from Business.business_correo import Business_logic_correo

# Instancio clase de Business
business_logic_users = BusinessLogic()
business_logic_empleados = BusinessLogic1()
business_payment = business_logic_payment()
empl = Empleado()
fac = Facturacion_de_pago()
enviar_correo = Business_logic_correo()

# Llamo a current app y a la funcion route
@app.route('/')
# Dentro creo una funcion que llame a la pagina index.html
def login():
    return render_template('index.html')



# Enviar login
@app.route('/enviarLogin', methods=['POST'])
def sendLogin():
    # Almaceno en una variable el valor del campo name del form 
    name = request.form['name']
    pwd = request.form['pwd']
    # Instancio la clase Entities
    user = User(name=name, pwd=pwd)
    # Llamo la funcion de login
    result = business_logic_users.login(user)
    # Y luego abre la plantilla result.html
    if result == 'Contraseña o usuario incorrecto.':
        return render_template('index.html', result=result)
    else:
        return render_template('resultLogin.html', result=result)



@app.route('/home')
def home():
    empleados = business_logic_empleados.procesar_seleccion_Empleado()
    return render_template('home.html', empleados=empleados)



@app.route('/seleccionEmpleado', methods = ['POST', 'GET'])
def getEmpleado():
    
    #Asigno los forms
    FechaDePago = request.form['Fecha']
    id_empleado = request.form.get('Empleado')

    # Trabajo con la fecha y su respuesta
    if FechaDePago:
        fecha = datetime.strptime(FechaDePago, '%Y-%m-%d').strftime('%d/%m/%Y')
    else:
        mensaje='No se ha seleccionado ninguna fecha, por favor intentalo de nuevo.'
        empleados = business_logic_empleados.procesar_seleccion_Empleado()
        return render_template('home.html', msj = mensaje, empleados=empleados)
    
    #Trabajo la informacion del usuario seleccionado
    almacen = business_logic_empleados.extraer_info_empleado_seleccionado(id_empleado)
    
    return render_template('info_empleado.html', almacen=almacen, fecha=fecha)




#Proceso la informacion de pago
@app.route('/procesarPago', methods = ['POST', 'GET'])
def procesoPago():

    #VARIABLES RECIBIDAS
    nombre = request.form['nombre']
    correo = request.form['correo']
    salario = float(request.form['salario'])
    fecha = request.form['fecha']

    #INIZIALIZO QUE ALTERAN EL SALARIO BRUTO
    horas_extras, bonos, ccss, pension, aso, renta = 0.0, 0.0, False, False, False, False

    #DEFINO VARIABLES QUE ALTERAN EL SALARIO BRUTO
    horas_extras = int(request.form.get('extrahours', 0))
    bonos = float(request.form.get('Bonos', 0.0))
    ccss = request.form.get('ccss', 'False')
    pension = request.form.get('pension', 'False') 
    aso = request.form.get('Asociacion', 'False') 
    renta = request.form.get('renta', 'False') 

    
    #REBAJOS DE SALARIO BRUTO:
    #Csss
    rebajoccss=business_payment.CCSS(ccss=ccss, nuevo_salario=salario)
    #Renta
    if renta == 'True':
        impuesto_renta=business_payment.calcular_renta_mensual(bruto_mensual=salario)  
    #Asociacion
    rebajo_aso=business_payment.calculo_asociacion(aso=aso, salario=salario)
    #Pension
    rebajo_pension=business_payment.calculo_pension(pension=pension)


    #APLICO REBAJOS
    salario_neto_con_rebajas=business_payment.salario_neto(pension=rebajo_pension, salario=salario, rebajo_aso=rebajo_aso, impuesto_renta=impuesto_renta, rebajoccss=rebajoccss)

    
    #EXTRAS DE SALARIO NETO:
    #Horas extras
    monto_horas_ext=business_payment.horas_extras(horas=horas_extras, salarioBruto=salario_neto_con_rebajas)

    salario_neto=business_payment.salario_neto_suma(monto_horas_ext=monto_horas_ext, bono=bonos, salario_neto_con_rebajas=salario_neto_con_rebajas)

    fac.generar_PDF(nombre=nombre, email=correo, salario_neto=salario_neto, fecha=fecha, salario_Bruto=salario, hextras=monto_horas_ext, bono=bonos, ccss=rebajoccss, pension=rebajo_pension, aso=rebajo_aso, renta=impuesto_renta, horase=horas_extras)
    mensaje='El comprobante de pago ha sido creado, el envío está siendo procesado...'
    enviar_correo.enviar_correo(destinatario=correo, remitente='milenio696@gmail.com', nombre=nombre)
    return mensaje

    
    

    
#EDICION DE EMPLEADOS

#Envio de los empleados
@app.route('/envio_empleados', methods = ['GET'])
def envio_empleados():
    empleados = business_logic_empleados.procesar_seleccion_Empleado()
    return render_template('empleados.html', empleados=empleados)




#Procesar empleado seleccionado empleado existente
@app.route('/seleccion_empleado', methods = ['POST', 'GET'])
def seleccion_empleado():
    id = request.form['Empleado']
    data = business_logic_empleados.extraer_info_empleado_seleccionado(id_empleado=id)
    empleados = business_logic_empleados.procesar_seleccion_Empleado()
    return render_template('empleados.html', data=data, empleados=empleados)




#Valido la creacion
@app.route('/crear_empleado', methods = ['POST', 'GET'])
def crear_empleado():
    validar = True
    empleados = business_logic_empleados.procesar_seleccion_Empleado()
    return render_template('empleados.html', validar=validar, empleados=empleados)



#Proceso la creacion
@app.route('/creacion', methods = ['POST', 'GET'])
def creacion():
    name=request.form['name']
    lastname=request.form['lastname']
    email=request.form['email']
    money=request.form['money']
    new_data = [name, lastname, email, money]
    business_logic_empleados.crear_empleado(new_data=new_data)
    empleados = business_logic_empleados.procesar_seleccion_Empleado()
    mensaje = 'El empleado ha sido exitosamente agregado en la base de datos!'
    return render_template('empleados.html', mensaje=mensaje, empleados=empleados)





# Procesos CRUD
@app.route('/edicion_empleado', methods = ['POST', 'GET'])
def edicion_empleado():
    
    #Variables generales
    mensaje=''    
    info = request.form['nombre'].split(' ', 2)
    nombre = info[0]

    if len(info)==1:
        apellido = ''
    elif len(info) == 2:
        apellido = info[1]
    elif len(info) > 2:
        apellido = info[1]+' '+info[2]   

    id_empleado = request.form['id']
    correo = request.form['correo']
    salario = request.form['salario']


    #Procesar edicion
    if request.form['action'] == 'update':
        data = [id_empleado, nombre, apellido, correo, salario]

        business_logic_empleados.editar_empleado(data=data)
        mensaje = 'El empleado ha sido exitosamente actualizado en la base de datos!'
    
    
    #Procesar eliminacion
    elif request.form['action'] == 'delete':
        id_empleado=request.form['id']
        business_logic_empleados.eliminar_empleado(id_empleado)   
        mensaje = 'El empleado ha sido exitosamente eliminado en la base de datos!'
    
    empleados=business_logic_empleados.procesar_seleccion_Empleado()
    return render_template('empleados.html', mensaje=mensaje, empleados=empleados)

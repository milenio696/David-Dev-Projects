from fpdf import FPDF
from Entities.pdf import PDF

class Facturacion_de_pago:

    def generar_PDF(self, nombre, email, salario_neto, fecha, salario_Bruto, hextras, bono, ccss, pension, aso, renta, horase):
        
        #Formateo todos los valores que van a ser mostrados, los redondeo a 2 decimales
        #-->  :, indica que es separador de miles, .2f es que se incluyan 2 decimales despues del punto
        salario_Bruto_formateado = f"{round(salario_Bruto, 2):,.2f}"
        salario_neto_formateado = f"{round(salario_neto, 2):,.2f}"      
        ccss_formateado = f"{round(ccss, 2):,.2f}"
        renta_formateado = f"{round(renta, 2):,.2f}"
        aso_formateado = f"{round(aso, 2):,.2f}"
        pension_formateado = f"{round(pension, 2):,.2f}"
        extras_formateado = f"{hextras:,.2f}"
        bono_formateado = f"{bono:,.2f}"


        #Envio los datos a entidades Factura
        info = PDF(informacion=(nombre, email, salario_neto, fecha))

        #Instancio libreria de PDF
        pdf=FPDF()

        #Empiezo con la creacion de la factura
        pdf.add_page()
        
        pdf.set_font('Times', size=18, style='B')
        pdf.cell(200, 10, txt="Comprobante de Pago", ln=True, align='C')

        #Añado espacio de linea adicional
        pdf.ln(10)

        #Introduzco el texto del PDF 
        pdf.set_font('Times', size=10)
        pdf.multi_cell(200, 10, txt=f'Agradecemos su cooperacion con la empresa, siempre es un gusto tenerlo como parte del equipo.')

        pdf.ln(10)

        pdf.cell(200, 10, txt=f'Nombre Completo: {nombre}.', ln=True)
        pdf.cell(200, 10, txt=f'Correo Electrónico: {email}.', ln=True)
        pdf.cell(200, 10, txt=f'Fecha de Pago: {fecha}.', ln=True)
        pdf.cell(200, 10, txt=f'Salario Bruto: {salario_Bruto_formateado} colones.', ln=True)
        pdf.cell(200, 10, txt=f'Monto Total a Pagar: {salario_neto_formateado} colones.', ln=True)

        pdf.ln(10)

        pdf.set_font('Times', size=12, style='B')
        pdf.cell(200, 10, txt=f'Desglose Completo de Rebajos: ', ln=True)

        pdf.set_font('Times', size=10)
        pdf.cell(200, 10, txt=f'Seguro Médico de la CCSS: {ccss_formateado} colones.', ln=True)
        pdf.cell(200, 10, txt=f'Impuesto de Renta: {renta_formateado} colones.', ln=True)
        pdf.cell(200, 10, txt=f'Asociación Solidarista: {aso_formateado} colones.', ln=True)
        pdf.cell(200, 10, txt=f'Pensión Alimenticia: {pension_formateado} colones.', ln=True)


        pdf.ln(10)

        pdf.set_font('Times', size=12, style='B')
        pdf.cell(200, 10, txt=f'Desglose de Compensaciones: ', ln=True)
        pdf.set_font('Times', size=10)    
        pdf.cell(200, 10, txt=f'{horase} Horas Extras Trabajadas: {extras_formateado} colones.', ln=True)
        pdf.cell(200, 10, txt=f'Bonificación: {bono_formateado} colones.', ln=True)

        #Exporto el archivo PDF
        nombre_PDF = f'Comprobante de Pago de {nombre}.pdf'
        pdf.output(nombre_PDF)
        

        


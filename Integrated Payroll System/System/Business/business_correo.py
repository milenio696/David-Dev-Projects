import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.application import MIMEApplication

class Business_logic_correo:
    

    def enviar_correo(self, destinatario, remitente, nombre):
        
        asunto = 'Comprobante Oficial de Pago'
        cuerpo = f"""
        Estimad@ {nombre},

        Espero que se encuentre de lo mas bien.

        El día de hoy ha recibido un pago a su cuenta bancaria D&J. Para más detalles, por favor, abrir el archivo adjunto.

        Si tiene alguna preocupación no dude en contactarnos al 8497-5903.
        
        Saludos cordiales,
        Servicio Bancario D&J
        """
        mensaje = MIMEMultipart()
        mensaje['From'] = remitente
        mensaje['To'] = destinatario
        mensaje['Subject'] = asunto

        mensaje.attach(MIMEText(cuerpo, 'plain'))
                      

        archivo_pdf = f'Z:\Ingeniería en Sistemas Computacionales\Programacion 3\Proyecto Final Programacion 3/Comprobante de Pago de {nombre}.pdf'
        with open(archivo_pdf, 'rb') as f:
            adjunto = MIMEApplication(f.read(), _subtype='pdf')
            adjunto.add_header('Content-Disposition', 'attachment', filename=f'Comprobante de Pago de {nombre}.pdf')
            mensaje.attach(adjunto)

        # Configurar el servidor SMTP y enviar el correo
        try:
            with smtplib.SMTP('smtp.gmail.com', 587) as server:
                server.starttls()  # Establecer conexión segura
                server.login('milenio696@gmail.com', 'epbx hfjq cdes ludi')
                server.send_message(mensaje)
                print("Correo enviado exitosamente")
        except Exception as e:
            print(f"Error al enviar el correo: {e}")

        
package com.ulatina.proyectoprogramacionv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TechSupportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tech_support)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia a los campos de texto
        val edtxtAsunto: EditText = findViewById(R.id.edtxt_asunto)
        val edtxtMensaje: EditText = findViewById(R.id.edtxt_mensaje)
        val btnEnviar: Button = findViewById(R.id.btn_enviar)
        val btn_atras:Button = findViewById(R.id.btn_atras_technical)

        btn_atras.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }


        // Configurar el botón de Enviar
        btnEnviar.setOnClickListener {
            val asunto = edtxtAsunto.text.toString()
            val mensaje = edtxtMensaje.text.toString()

            // Verificar que ambos campos no estén vacíos
            if (asunto.isNotEmpty() && mensaje.isNotEmpty()) {
                // Crear el intent para abrir la aplicación de correo
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "message/rfc822" // Especifica que se trata de un correo electrónico
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("david.abarca3@ulatina.net")) // Dirección del soporte
                intent.putExtra(Intent.EXTRA_SUBJECT, asunto) // Asunto del correo
                intent.putExtra(Intent.EXTRA_TEXT, mensaje) // Cuerpo del mensaje

                // Intent para enviar el correo
                startActivity(Intent.createChooser(intent, "Enviar correo con:"))

            }
        }
    }
}
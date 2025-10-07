package com.ulatina.proyectoprogramacionv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth //Inizializa Firebase Auth mas adelante

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Defino las variables de los componentes

        val edtxt_nombre_nuevo : TextView = findViewById(R.id.edtxt_new_username)
        val edtxt_email_nuevo : TextView = findViewById(R.id.edtxt_new_email)
        val txt_new_password1 : TextView = findViewById(R.id.edtxt_new_password)
        val txt_new_password2 : TextView = findViewById(R.id.edtxt_confirm_password)
        val btn_crear_cuenta : Button = findViewById(R.id.btn_crear_cuenta)
        val btn_atras : Button = findViewById(R.id.btn_atras_sign_up)
        firebaseAuth = Firebase.auth


        //Accion del boton "Atras"
        btn_atras.setOnClickListener(){
            val i = Intent(this, MainActivity :: class.java)
            startActivity(i)
        }

        //Accion del boton "Crear Cuenta"
        btn_crear_cuenta.setOnClickListener(){

            var pass1 = txt_new_password1.text.toString()
            var pass2 = txt_new_password2.text.toString()

            //Creo comparacion
            if (pass1.equals(pass2) && pass1 != null && pass2 != null){
                createAccount(edtxt_email_nuevo.text.toString(), txt_new_password2.text.toString())
            } else{
                Toast.makeText(baseContext,"Error: Las contraseñas no coinciden o algún campo está vacío.", Toast.LENGTH_SHORT).show()
                txt_new_password2.requestFocus() //Señalo que corrijan el password 2
            }
        }


    } //Fin de funcion onCreate


    //Funcion de creacion de cuenta
    private fun createAccount(email:String, pass:String){

        //Llamo a funcion en Firebase de creacion de usuario
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Toast.makeText(baseContext,"La cuenta ha sido creada exitosamente!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(baseContext,"Algo salió mal: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }

    }

}
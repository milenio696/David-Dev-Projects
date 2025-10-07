package com.ulatina.proyectoprogramacionv

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.credentials.CredentialManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    //Variables no nulas
    private lateinit var firebaseAuth : FirebaseAuth //Inizializa Firebase Auth mas adelante
    private lateinit var authStateListener : FirebaseAuth.AuthStateListener //Libreria que lee cambios en la autenticacion
    private lateinit var credentialManager: CredentialManager

    private val RC_SIGN_IN = 9001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }






        //*******************************************************************************
        //Terminos y condiciones
        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        mostrarDialogoTerminos(prefs)



        //**************************************************************************
        //Pregunto por los permisos de notificacion apenas abre el app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }

        //Inizializo las variables de los componentes del Main Layout
        val btn_login : Button = findViewById(R.id.btn_login)
        val edtxt_email : TextView = findViewById(R.id.edtxt_email_address)
        val edtxt_password : TextView = findViewById(R.id.edtxt_password)
        val btn_registrar : Button = findViewById(R.id.btn_registrar)

        firebaseAuth = Firebase.auth


        //Codifico el boton "Ingresar"
        btn_login.setOnClickListener{
            val email = edtxt_email.text.toString()
            val pass = edtxt_password.text.toString()

            if (email.isEmpty() && pass.isEmpty() || email.isEmpty() || pass.isEmpty()){
                Toast.makeText(baseContext, "El correo y/o la contreseña están vacías.", Toast.LENGTH_SHORT).show()
            } else{
                signIn(email, pass) } // .text.toString() devuelve un valor editable que se convertira en string
            }


        
        //Codifico el boton "Registrar"
        btn_registrar.setOnClickListener{
            val i = Intent(this, CreateAccountActivity::class.java)
            startActivity(i)
        }

    } //Fin de funcion OnCreate




    //Funcion que verificar si los datos del login son correctos
    private fun signIn(email:String, password:String){

        firebaseAuth.signInWithEmailAndPassword(email, password)

            // Agrega un listener que se ejecuta cuando la autenticación se completa en este activity guardando el resultado en un task
            .addOnCompleteListener(this){ task ->

                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, "Autenticacion Exitosa. Bienvenido!", Toast.LENGTH_SHORT).show()
                    //Ahora me dirijo a la pantalla home
                    val i = Intent(this, HomeActivity :: class.java)
                    startActivity(i)

                } //Fin de conficional
                else{
                    Toast.makeText(baseContext,"Correo/contraseña incorrecta(s).", Toast.LENGTH_LONG).show()
                }

        } //Fin de Task

    } //Fin de funcion singIn




    //Funcion de terminos y condiciones
    private fun mostrarDialogoTerminos(prefs: SharedPreferences) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Términos y Condiciones")
        builder.setMessage("Al usar esta aplicación, aceptas nuestros Términos y Condiciones. Por favor, revísalos antes de continuar.")

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            prefs.edit().putBoolean("acepto_terminos", true).apply()
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
            finish() // Cierra la app si no acepta
        }

        builder.setCancelable(false) // Impide cerrarlo tocando fuera
        builder.show()
    }



//*******************************************************************************************************************



} //Fin de la clase Main
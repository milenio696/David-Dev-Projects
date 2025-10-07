package com.ulatina.proyectoprogramacionv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class HomeActivity : AppCompatActivity() {

    //Inizializa Firebase Auth mas adelante
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Inizializo variables
        firebaseAuth = Firebase.auth
        val btn_estudiantes : ImageButton = findViewById(R.id.btn_estudiantes)
        val btn_matricula : ImageButton = findViewById(R.id.btn_matricula)
        val btn_soporte_tecnico : ImageButton = findViewById(R.id.btn_soporte_tecnico)
        val txt_bienvenida : TextView = findViewById(R.id.txt_bienvenida)
        val user = firebaseAuth.currentUser
        val firstName = user?.email?.substringBefore("@")?.replaceFirstChar { it.uppercase() } ?: "Usuario"

        txt_bienvenida.text = "  Bienvenido $firstName!"


        //Redirecciono a la base de datos de los estudiantes
        btn_estudiantes.setOnClickListener{
            val i = Intent(this, EstudiantesHomeActivity::class.java)
            startActivity(i)
        }

        //Redirecciono al proceso de matricula
        btn_matricula.setOnClickListener{

            val dialog = AlertDialog.Builder(this)
                .setMessage("¿A que seccion deseas ir?")
                .setPositiveButton("Matricula") { _, _ ->

                    val i = Intent(this, MatriculaActivity::class.java)
                    startActivity(i)

                }
                .setNegativeButton("Materias matriculadas"){ _, _ ->
                    val i = Intent(this, MateriasMatriculadasActivity::class.java)
                    startActivity(i)
                }
                .create()

            dialog.show()

        }

        //Redirecciono a la seccion de tech support
        btn_soporte_tecnico.setOnClickListener{
            val i = Intent(this, TechSupportActivity::class.java)
            startActivity(i)
        }

    }
}
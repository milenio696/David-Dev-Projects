package com.ulatina.proyectoprogramacionv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class CrearEstudianteActivity : AppCompatActivity() {

    //Creo variable de la DB
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_estudiante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtxt_genero : EditText = findViewById(R.id.edtxt_genero_estudiante)
        val btn_atras : Button = findViewById(R.id.btn_atras_crear_estudiante)
        val btn_insertar_estudiante : Button = findViewById(R.id.btn_crear_estudiante)
        val generos = arrayOf("Masculino", "Femenino")
        val edtxt_carrera : EditText = findViewById(R.id.edtxt_carrera_estudiante)
        val carreras = arrayOf(
            "Ingeniería en Sistemas",
            "Ingeniería en Software",
            "Ingeniería Electrónica",
            "Ingeniería Mecánica",
            "Ingeniería Civil",
            "Ingeniería Industrial",
            "Ingeniería Biomédica",
            "Ingeniería en Telecomunicaciones",
            "Ingeniería en Inteligencia Artificial",
            "Ingeniería Aeroespacial"
        )




        // Mostrar opciones seleccionables de los generos
        edtxt_genero.setOnClickListener {
            val popUp1 = AlertDialog.Builder(this)
            popUp1.setTitle("Selecciona un Genero")
            popUp1.setItems(generos) { _, which ->
                edtxt_genero.setText(generos[which])
            }
            popUp1.show()
        }

        // Mostrar opciones seleccionables de las carreras
        edtxt_carrera.setOnClickListener {
            val popUp2 = AlertDialog.Builder(this)
            popUp2.setTitle("Selecciona una Carrera")
            popUp2.setItems(carreras) { _, which ->
                edtxt_carrera.setText(carreras[which])
            }
            popUp2.show()
        }


        btn_atras.setOnClickListener{
            val i = Intent(this, EstudiantesHomeActivity::class.java)
            startActivity(i)
        }


        btn_insertar_estudiante.setOnClickListener{
            crearEstudiante()
        }


    }

    private fun crearEstudiante(){
        val txt_nombre : TextView = findViewById(R.id.edtxt_nombre_estudiante)
        val txt_apellido : TextView = findViewById(R.id.edtxt_apellido_estudiante)
        val txt_genero : TextView = findViewById(R.id.edtxt_genero_estudiante)
        val txt_carrera : TextView = findViewById(R.id.edtxt_carrera_estudiante)
        val txt_cedula : TextView = findViewById(R.id.edtxt_cedula_estudiante)
        val txt_telefono : TextView = findViewById(R.id.edtxt_numero_estudiante)


        var name: String = txt_nombre.text.toString()
        var apell: String = txt_apellido.text.toString()
        var carr : String = txt_carrera.text.toString()
        var cedu: String = txt_cedula.text.toString()
        var gen : String = txt_genero.text.toString()
        var tel : String = txt_telefono.text.toString()


        //Datos para documento
        val data = hashMapOf(
            "Nombre" to name,
            "Apellido" to apell,
            "Cedula" to cedu,
            "Carrera" to carr,
            "Genero" to gen,
            "Numero de Telefono" to tel
        )
        db.collection("Estudiantes")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(baseContext, "Estudiante creado exitosamente!", Toast.LENGTH_SHORT).show()
            }

            .addOnFailureListener{ e -> }

        val i = Intent(this, EstudiantesHomeActivity::class.java)
        startActivity(i)


    }




}
package com.ulatina.proyectoprogramacionv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class EdicionEstudianteActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    lateinit var nombreEditText: EditText
    lateinit var apellidoEditText: EditText
    lateinit var cedulaEditText: EditText
    lateinit var generoEditText: EditText
    lateinit var carreraEditText: EditText
    lateinit var telefonoEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edicion_estudiante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//*****************************************************************************************
        //INICIALIZO VARIABLES

        nombreEditText = findViewById(R.id.edtxt_nombre_edicion)
        apellidoEditText = findViewById(R.id.edtxt_apellido_edicion)
        cedulaEditText = findViewById(R.id.edtxt_cedula_edicion)
        generoEditText = findViewById(R.id.edtxt_genero_edicion)
        carreraEditText = findViewById(R.id.edtxt_carrera_edicion)
        telefonoEditText = findViewById(R.id.edtxt_telefono_edicion)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        val btn_atras : Button = findViewById(R.id.btn_atras_edicion_estudiante)
        val btn_actualizar: Button = findViewById(R.id.btn_actualizar_edicion)
        val btn_eliminar: Button = findViewById(R.id.btn_eliminar_estudiante)


        // Obtener el ID enviado desde el intent
        val id = intent.getStringExtra("ID")
        val coleccionEstudiantes = db.collection("Estudiantes") //Agrego la coleccion de la DB





//***************************************************************************************
        //IMPLEMENTO LA CONFIGURACION DE LOS CAMPOS


        val generos = arrayOf("Masculino", "Femenino")
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
        generoEditText.setOnClickListener {
            val popUp1 = AlertDialog.Builder(this)
            popUp1.setTitle("Selecciona un Genero")
            popUp1.setItems(generos) { _, which ->
                generoEditText.setText(generos[which])
            }
            popUp1.show()
        }

        // Mostrar opciones seleccionables de las carreras
        carreraEditText.setOnClickListener {
            val popUp2 = AlertDialog.Builder(this)
            popUp2.setTitle("Selecciona una Carrera")
            popUp2.setItems(carreras) { _, which ->
                carreraEditText.setText(carreras[which])
            }
            popUp2.show()
        }

        //Boton de atras
        btn_atras.setOnClickListener{
            val i = Intent(this, EstudiantesHomeActivity::class.java)
            startActivity(i)
        }



//**************************************************************************************************
        // EXTRACCION DE LA INFORMACION EN FIRESTORE

            // Verificamos que el ID no sea nulo y consultamos en Firebase
            if (!id.isNullOrEmpty()) {
                obtenerDatosDesdeFirestore(id)
            } else {
                Toast.makeText(this, "Error: No se recibió el ID", Toast.LENGTH_SHORT).show()
            }





//********************************************************************************************
        //ACTUALIZAR Y ELIMINAR ESTUDIANTE


        //Actualizar
        btn_actualizar.setOnClickListener{

            // Inicializar referencias de EditTexts

            var name: String = nombreEditText.text.toString()
            var apell: String = apellidoEditText.text.toString()
            var carr : String = carreraEditText.text.toString()
            var cedu: String = cedulaEditText.text.toString()
            var gen : String = generoEditText.text.toString()
            var tel : String = telefonoEditText.text.toString()

            val idUnico = id.toString()
            val docActualizado = HashMap<String, Any>()
            docActualizado["Nombre"] = name
            docActualizado["Apellido"] = apell
            docActualizado["Carrera"] = carr
            docActualizado["Genero"] = gen
            docActualizado["Cedula"] = cedu
            docActualizado["Numero de Telefono"] = tel

            coleccionEstudiantes.document(idUnico)
                .update(docActualizado)
                .addOnSuccessListener {
                    Toast.makeText(baseContext, "Estudiante actualizado exitosamente!", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, EstudiantesHomeActivity::class.java)
                    startActivity(i)
                }
                .addOnFailureListener{ e ->
                    Toast.makeText(baseContext, "Error: ${e.message}.", Toast.LENGTH_SHORT).show()
                }

        }

        //Eliminar
        btn_eliminar.setOnClickListener{
            var ID : String = id.toString()
            var name: String = nombreEditText.text.toString()
            val dialog = AlertDialog.Builder(this)
                .setTitle("Confirmación de eliminación")
                .setMessage("¿Seguro que deseas eliminar al estudiante '{$name$}' ?")
                .setPositiveButton("Sí, eliminar") { _, _ ->

                    // Aquí elimino el estudiante
                        db.collection("Estudiantes").document(ID)
                            .delete()
                            .addOnSuccessListener {
                                Toast.makeText(this, "Estudiante eliminado exitosamente", Toast.LENGTH_SHORT).show()
                                val i = Intent(this, EstudiantesHomeActivity::class.java)
                                startActivity(i)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error al eliminar: ${e.message}", Toast.LENGTH_SHORT).show()
                            }

                }
                .setNegativeButton("Cancelar", null)
                .create()

            dialog.show()
        }



    }



    //FUNCION DE EXTRACCION DE DATOS
    private fun obtenerDatosDesdeFirestore(id: String) {

        // Acceder a la colección "Estudiantes" y obtener el documento con el ID
        db.collection("Estudiantes").document(id).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {

                    // Convertir el documento a un objeto Estudiantes
                    val estudiante = document.toObject(Estudiantes::class.java)

                    //Llamo a las variables segun la informacion almacenada con el ID a traves de los nombres de los campos
                    estudiante?.let {
                        nombreEditText.setText(document.getString("Nombre") ?: "No disponible")
                        apellidoEditText.setText(document.getString("Apellido") ?: "No disponible")
                        cedulaEditText.setText(document.getString("Cedula") ?: "No disponible")
                        generoEditText.setText(document.getString("Genero") ?: "No disponible")
                        carreraEditText.setText(document.getString("Carrera") ?: "No disponible")
                        telefonoEditText.setText(document.getString("Numero de Telefono") ?: "No disponible")
                        Toast.makeText(this, "El estudiante ha sido encontrado exitosamente!", Toast.LENGTH_SHORT).show()

                    }
                } else {
                    Toast.makeText(this, "No se encontró el estudiante", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al obtener datos: ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }



}
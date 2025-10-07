package com.ulatina.proyectoprogramacionv

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class EstudiantesHomeActivity : AppCompatActivity(), EstudiantesAdapter.OnItemClickListener {


    private val db = FirebaseFirestore.getInstance() //Creo variable de la DB
    private val coleccionEstudiantes = db.collection("Estudiantes") //Agrego la coleccion de la DB
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EstudiantesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estudiantes_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        consultaColeccionDB()

        //Inizializo variables
        val btn_atras_estudiante : Button = findViewById(R.id.btn_atras_estudiantes)
        val btn_crear_estudiante : Button = findViewById(R.id.btn_crear_estudiante)


        //Redirecciono al main menu
        btn_atras_estudiante.setOnClickListener{
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

        //Redirecciono a pantalla de crear estudiante
        btn_crear_estudiante.setOnClickListener{
            val i = Intent(this, CrearEstudianteActivity::class.java)
            startActivity(i)
        }


        //TRAIGO LOS DATOS DE LA DB

        //Interrelaciono los componentes
        recyclerView = findViewById(R.id.list_estudiantes)
        recyclerView.layoutManager = LinearLayoutManager(this) //Selecciono el tipo de layout usado
        adapter = EstudiantesAdapter(this) // Inicializar el adapter
        recyclerView.adapter = adapter //Al recycle view le asigno el adapter ya configurado


    }

    //Funcion de consulta a la DB
    fun consultaColeccionDB() {
        coleccionEstudiantes.get()
            .addOnSuccessListener { querySnapshot ->
                val listModel = mutableListOf<Estudiantes>()
                for (document in querySnapshot) {
                    val id = document.id
                    val nombre = document.getString("Nombre")
                    val cedula = document.getString("Cedula")

                    if (nombre != null && cedula != null ) {
                        val modelo = Estudiantes(id, nombre, cedula)
                        listModel.add(modelo)
                    }
                }
                adapter.setDatos(listModel)
            }
    }

    override fun onItemClick(modeloEstudiantes: Estudiantes) {

        val intent = Intent(this, EdicionEstudianteActivity::class.java).apply {
            putExtra("ID", modeloEstudiantes.id)

        }

        startActivity(intent)
    }

}
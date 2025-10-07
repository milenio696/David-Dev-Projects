package com.ulatina.proyectoprogramacionv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.ulatina.proyectoprogramacionv.com.ulatina.proyectoprogramacionv.TarjetaAdapter
import com.ulatina.proyectoprogramacionv.com.ulatina.proyectoprogramacionv.Tarjetas

class AdministrarTarjetasActivity : AppCompatActivity(), TarjetaAdapter.OnItemClickListener {

    private val db = FirebaseFirestore.getInstance() //Creo variable de la DB
    private val coleccionTarjetas = db.collection("Tarjetas") //Agrego la coleccion de la DB
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TarjetaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_administrar_tarjetas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        consultaColeccionDB()

        //Interrelaciono los componentes
        recyclerView = findViewById(R.id.list_tarjetas)
        recyclerView.layoutManager = LinearLayoutManager(this) //Selecciono el tipo de layout usado
        adapter = TarjetaAdapter(this) // Inicializar el adapter
        recyclerView.adapter = adapter //Al recycle view le asigno el adapter ya configurado



        val btn_listo : Button = findViewById(R.id.btn_listo_admin_tarjetas)
        btn_listo.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

    }



    //**********************************************************************
    //Funcion de consulta a la DB
    fun consultaColeccionDB() {
        coleccionTarjetas.get()
            .addOnSuccessListener { querySnapshot ->
                val listModel = mutableListOf<Tarjetas>()
                for (document in querySnapshot) {
                    val id = document.id
                    val numero = document.getString("Tarjeta")
                    val nombre = document.getString("Dueño")

                    if (nombre != null && numero != null ) {
                        val modelo = Tarjetas(nombre, numero, id)
                        listModel.add(modelo)
                    }
                }
                adapter.setDatos(listModel)
            }
    }



    override fun onItemClick(modeloTarjetas: Tarjetas) {

        val dialog = AlertDialog.Builder(this)
            .setMessage("¿Seguro que deseas eliminar la tarjeta seleccionada?")
            .setPositiveButton("Sí, eliminar") { _, _ ->

                // Aquí elimino la tarjeta
                db.collection("Tarjetas").document(modeloTarjetas.id)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(this, "Tarjeta eliminado exitosamente", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, AdministrarTarjetasActivity::class.java)
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
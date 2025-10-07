package com.ulatina.proyectoprogramacionv

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.ulatina.proyectoprogramacionv.com.ulatina.proyectoprogramacionv.CursosAdapter
import com.ulatina.proyectoprogramacionv.com.ulatina.proyectoprogramacionv.Tarjetas


class MateriasMatriculadasActivity : AppCompatActivity(), CursosAdapter.OnItemClickListener {

    private val chanel_id = "1000"

    private val db = FirebaseFirestore.getInstance() //Creo variable de la DB
    private val coleccionCursos = db.collection("Cursos") //Agrego la coleccion de la DB
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CursosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_materias_matriculadas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        consultaColeccionDB()

        val btn_listo : Button = findViewById(R.id.btn_listo_curso)

        //Interrelaciono los componentes
        recyclerView = findViewById(R.id.list_cursos)
        recyclerView.layoutManager = LinearLayoutManager(this) //Selecciono el tipo de layout usado
        adapter = CursosAdapter(this) // Inicializar el adapter
        recyclerView.adapter = adapter //Al recycle view le asigno el adapter ya configurado


        btn_listo.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }


    }




    //**********************************************************************
    //Funcion de consulta a la DB
    fun consultaColeccionDB() {
        coleccionCursos.get()
            .addOnSuccessListener { querySnapshot ->
                val listModel = mutableListOf<Cursos>()

                for (document in querySnapshot) {
                    val id = document.id
                    val materias = document["Curso"] as? ArrayList<String> ?: arrayListOf()
                    val estado = document.getString("Estado") ?: ""

                    // Si no hay materias, eliminar el documento completo
                    if (materias.isEmpty()) {
                        db.collection("Cursos").document(id)
                            .delete()
                            .addOnSuccessListener {
                                Log.d("Firestore", "Documento $id eliminado porque no tenía materias")
                            }
                            .addOnFailureListener { e ->
                                Log.e("Firestore", "Error al eliminar documento: ${e.message}")
                            }
                        continue // No agregar a la lista
                    }

                    val curso = Cursos(materias, estado, id)
                    listModel.add(curso)
                }

                adapter.setDatos(listModel)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al consultar cursos: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }







    override fun onItemClick(modeloCursos: Cursos) {
        val materiasArray = modeloCursos.materias.toTypedArray()

        val dialog = AlertDialog.Builder(this)
            .setTitle("Selecciona la materia a congelar")
            .setItems(materiasArray) { _, which ->
                val materiaSeleccionada = materiasArray[which]

                // Confirmar congelamiento
                AlertDialog.Builder(this)
                    .setMessage("¿Seguro que deseas congelar '$materiaSeleccionada'?")
                    .setPositiveButton("Sí, congelar") { _, _ ->
                        val nuevasMaterias = modeloCursos.materias.filter { it != materiaSeleccionada }

                        db.collection("Cursos").document(modeloCursos.id)
                            .update("Curso", nuevasMaterias)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Materia congelada correctamente", Toast.LENGTH_SHORT).show()
                                crearNotificacion()
                                enviarNotificacion()
                                consultaColeccionDB()
                                }
                                    .addOnFailureListener { e ->
                                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }



    //*****************************************************************************
    //MANEJO DE NOTIFICACION

    private fun crearNotificacion(){

        //Comparo que la version usada sea mayor a la Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Canal 1000"
            val descripcion = "Canal de Notificacion"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val chanel = NotificationChannel(chanel_id, name, importance).apply {
                description=descripcion
            }

            //Creo el canal para enviar notificacion
            val notificationManager : NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(chanel)

        }

    }


    private fun enviarNotificacion() {

        //Establezco todos lo spermisos para la notificacion
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                1
            )

        } else {
            val mensaje = "¡Congelamiento exitoso! En caso de querer reactivarla, contáctanos al Soporte Tecnico."
            val builder = NotificationCompat.Builder(this,chanel_id)
                .setSmallIcon(R.drawable.ico_materia)
                .setContentTitle("¡Confirmado!")
                .setContentText(mensaje)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(this)) {
                notify(1, builder.build())
            }
        }
    }

}





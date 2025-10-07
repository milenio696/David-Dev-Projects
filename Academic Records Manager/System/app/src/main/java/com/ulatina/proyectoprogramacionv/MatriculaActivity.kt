package com.ulatina.proyectoprogramacionv

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import java.util.Calendar


class MatriculaActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    // Variables donde el usuario mete los datos
    private lateinit var edtxtTajeta: EditText
    private lateinit var edtxtFechaExpiracion: EditText
    private lateinit var edtxtCvv: EditText
    private lateinit var edtxtDueño: EditText

    // Variables donde se reflejan los cambios en tiempo real
    private lateinit var txtRTtarjeta: TextView
    private lateinit var txtRTfecha: TextView
    private lateinit var txtRTcvv: TextView
    private lateinit var txtRTdueño: TextView


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_matricula)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    //*******************************************************************************

        // Inicializo variables
        edtxtTajeta = findViewById(R.id.edtxt_tajeta)
        edtxtFechaExpiracion = findViewById(R.id.edtxt_fecha_expiracion)
        edtxtCvv = findViewById(R.id.edtxt_cvv)
        edtxtDueño = findViewById(R.id.edtxt_dueño)
        val btn_confirmar_compra : Button = findViewById(R.id.btn_confirmar_compra)
        val edtxt_materias : EditText = findViewById(R.id.edtxt_curso)
        val btn_cancelar : Button = findViewById(R.id.btn_cancelar_compra)
        val btn_ver_tarjetas : Button = findViewById(R.id.btn_admin_tarjetas)

        txtRTtarjeta = findViewById(R.id.txt_RTtarjeta)
        txtRTfecha = findViewById(R.id.txt_RTfecha)
        txtRTcvv = findViewById(R.id.txt_RTcvv)
        txtRTdueño = findViewById(R.id.txt_RTdueño)


        //Llamo la funcion de actualizacion de tarjeta
        actualizarTarjeta()


        //*************************************************************************************
        //Modifico el campo de fecha
        edtxtFechaExpiracion.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)

            // Creamos el DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, _ ->
                    // Formateamos el mes y año seleccionado en el formato MM/AA
                    val formattedDate = String.format("%02d/%02d", selectedMonth + 1, selectedYear % 100)
                    // Actualizamos el EditText con la fecha seleccionada
                    edtxtFechaExpiracion.setText(formattedDate)
                },
                year, month, 1 // Establecemos el valor inicial al mes y año actuales
            )

            // Mostramos el DatePicker
            datePickerDialog.show()
        }

        //Boton de crear tarjeta
        btn_confirmar_compra.setOnClickListener{
            crearTarjeta()
            crearCurso()

            txtRTtarjeta.setText("")
            txtRTfecha.setText("")
            txtRTcvv.setText("")
            txtRTdueño.setText("")
            edtxt_materias.setText("")
        }

        //Boton de Cancelar Compra
        btn_cancelar.setOnClickListener{
            val i= Intent(this, HomeActivity::class.java)
            startActivity(i)
        }


        //Campo de materia por matricular

        val materiasGenerales = arrayOf(
            "Matemáticas Avanzadas I",
            "Física II",
            "Estadística y Probabilidad",
            "Geometría Descriptiva",
            "Cálculo Multivariable",
            "Álgebra Lineal Avanzada",
            "Ética en la Ingeniería",
            "Economía para Ingenieros"
        )

        val materiasTecnicas = arrayOf(
            "Teoría de Circuitos Avanzados",
            "Sistemas de Control",
            "Electrónica de Potencia",
            "Microelectrónica",
            "Análisis de Señales y Sistemas",
            "Mecánica de Fluidos",
            "Termodinámica Avanzada",
            "Redes de Comunicaciones Avanzadas"
        )

        btn_ver_tarjetas.setOnClickListener{
            val i = Intent(this, AdministrarTarjetasActivity::class.java)
            startActivity(i)
        }





        // Mostrar las opciones de materias cuando se hace clic en el campo
        edtxt_materias.setOnClickListener {
            val popUpMaterias = AlertDialog.Builder(this)
            popUpMaterias.setTitle("Selecciona las Materias")

            // Crear una lista combinada de todas las materias
            val todasLasMaterias = materiasGenerales + materiasTecnicas

            // Crear un array booleano para controlar qué elementos están seleccionados
            val selectedItems = BooleanArray(todasLasMaterias.size)

            popUpMaterias.setMultiChoiceItems(todasLasMaterias, selectedItems) { _, which, isChecked ->
                selectedItems[which] = isChecked
            }

            // Botón de Aceptar
            popUpMaterias.setPositiveButton("Aceptar") { _, _ ->
                // Filtrar las materias seleccionadas
                val materiasSeleccionadas = mutableListOf<String>()
                for (i in selectedItems.indices) {
                    if (selectedItems[i]) {
                        materiasSeleccionadas.add(todasLasMaterias[i])
                    }
                }

                // Mostrar las materias seleccionadas en el campo de texto
                edtxt_materias.setText(materiasSeleccionadas.joinToString(", "))
            }

            // Botón de Cancelar
            popUpMaterias.setNegativeButton("Cancelar", null)

            // Mostrar el diálogo
            popUpMaterias.show()
        }






    }



    //************************************************************
    //Agregar los cursos seleccionados a la BD


    private fun crearCurso() {
        val txtCurso: TextView = findViewById(R.id.edtxt_curso)
        val materiasTexto: String = txtCurso.text.toString()

        // Separar materias por coma y limpiar espacios
        val materias: ArrayList<String> = materiasTexto.split(",")
            .map { it.trim() } //Remueve espacios
            .filter { it.isNotEmpty() } //Se fija que no este vacio
            .toCollection(ArrayList()) //Lo almacena en el arraylist

        // Crear el documento
        val data = hashMapOf(
            "Curso" to materias,
            "Estado" to "Matriculado" //Seteando valor por defecto
        )

        db.collection("Cursos")
            .add(data)
            .addOnSuccessListener {
                Toast.makeText(baseContext, "Curso(s) matriculado(s) exitosamente!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(baseContext, "Error al crear el curso: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }





    //*******************************************************************************
    //LOGICA PARA ACTUALZAR A TIEMPO REAL EL TEXTO ENCIMA DE LA TARJETA

    // Logica de actualizacion a tiempo real
    private fun actualizarTarjeta(){

        // Tarjeta
        edtxtTajeta.addTextChangedListener(object : TextWatcher { //El textwatcher monitorea los cambios de un edtxt
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {} //Charsequence logra almacenar los chars del edtxt junto con su posicion en los INT

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, after: Int) {
                // Actualizamos el TextView de la tarjeta en tiempo real
                txtRTtarjeta.text = formatoDeTarjeta(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable?) {}
        })



        //Fecha de expiracion
        edtxtFechaExpiracion.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, after: Int) {
                txtRTfecha.text = charSequence.toString()
            }

            override fun afterTextChanged(editable: Editable?) {}
        })



        //CVV
        edtxtCvv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, after: Int) {
                txtRTcvv.text = charSequence.toString()
            }

            override fun afterTextChanged(editable: Editable?) {}
        })



        // Dueño del titular
        edtxtDueño.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, after: Int) {
                txtRTdueño.text = charSequence.toString()
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }

    // Función para ponerle un formato al número de la tarjeta
    private fun formatoDeTarjeta(cardNumber: String): String {
        val formatted = StringBuilder()
        for (i in cardNumber.indices) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(" ")
            }
            formatted.append(cardNumber[i])
        }
        return formatted.toString()
    }





    //**************************************************************************************************
    //CREACION DE LA TARJETA EN LA BASE DE DATOS

    private fun crearTarjeta(){
        // Inicializo las variables
        val edtxtTajeta: TextView = findViewById(R.id.edtxt_tajeta)
        val edtxtFechaExpiracion: TextView = findViewById(R.id.edtxt_fecha_expiracion)
        val edtxtCvv: TextView = findViewById(R.id.edtxt_cvv)
        val edtxtDueño: TextView = findViewById(R.id.edtxt_dueño)

        // Obtengo los datos introducidos por el usuario
        val tajeta: String = edtxtTajeta.text.toString()
        val fechaExpiracion: String = edtxtFechaExpiracion.text.toString()
        val cvv: String = edtxtCvv.text.toString()
        val dueño: String = edtxtDueño.text.toString()

        // Datos para el documento
        val data = hashMapOf(
            "Tarjeta" to tajeta,
            "Fecha Expiracion" to fechaExpiracion,
            "CVV" to cvv,
            "Dueño" to dueño
        )

        // Guardar en la base de datos
        db.collection("Tarjetas")
            .add(data)
            .addOnSuccessListener { documentReference -> }
            .addOnFailureListener { e ->
                Toast.makeText(baseContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}
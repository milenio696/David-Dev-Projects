package com.ulatina.proyectoprogramacionv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EstudiantesAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<EstudiantesAdapter.ViewHolder> () {

    private var datos : List<Estudiantes> = ArrayList()

    interface OnItemClickListener{
        fun onItemClick(modeloEstudiantes: Estudiantes)
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val txtNombreEstudiante: TextView = itemView.findViewById(R.id.txt_nombre_estudiante_layout)
        val txtID: TextView = itemView.findViewById(R.id.ID_layout)
        val txtCedula: TextView = itemView.findViewById(R.id.txt_cedula_estudiante_layout)

        init {
            // Configuramos el evento de clic en cada elemento del RecyclerView
            itemView.setOnClickListener {
                // Evitamos que el usuario haga clic varias veces rápidamente
                if (!itemView.isClickable) return@setOnClickListener
                itemView.isClickable = false

                // Agregamos una animación de "presionado" para mejorar la experiencia del usuario
                itemView.animate()
                    .scaleX(0.95f) // Reduce ligeramente el tamaño en el eje X
                    .scaleY(0.95f) // Reduce ligeramente el tamaño en el eje Y
                    .setDuration(100) // La animación dura 100ms
                    .withEndAction { // Una vez terminada la reducción, volvemos al tamaño original
                        itemView.animate().scaleX(1f).scaleY(1f).setDuration(100)

                        // Obtenemos la posición del elemento que se ha clicado
                        val position = adapterPosition

                        // Verificamos que la posición sea válida (que no sea NO_POSITION)
                        if (position != RecyclerView.NO_POSITION) {
                            val modeloEstudiantes = datos[position] // Obtenemos el estudiante de la lista

                            // Llamamos a la función de clic para que se realice la acción deseada
                            itemClickListener.onItemClick(modeloEstudiantes)

                        }

                        // Después de 500ms, permitimos que el usuario vuelva a hacer clic
                        itemView.postDelayed({ itemView.isClickable = true }, 500)
                    }
            }
        }
    }

    //Muestra la vista. El false es si se quiere ajustar automaticamente al viewgroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.estudiantes_layout, parent, false)
        return ViewHolder(view)
    }

    //Especifico la cantidad de campos por item
    override fun getItemCount(): Int {
        return datos.size
    }

    fun setDatos(datos : List<Estudiantes>){
        this.datos = datos
        notifyDataSetChanged()
    }



    //Llena la vista con la infomracion correspondiente
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estudiante = datos[position]
        holder.txtID.text = estudiante.id
        holder.txtNombreEstudiante.text = estudiante.nombre
        holder.txtCedula.text = estudiante.cedula
    }
}
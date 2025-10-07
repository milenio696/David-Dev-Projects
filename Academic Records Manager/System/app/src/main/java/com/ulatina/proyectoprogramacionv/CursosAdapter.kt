package com.ulatina.proyectoprogramacionv.com.ulatina.proyectoprogramacionv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulatina.proyectoprogramacionv.Cursos
import com.ulatina.proyectoprogramacionv.R

class CursosAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<CursosAdapter.ViewHolder>() {

    private var datos: List<Cursos> = ArrayList()

    interface OnItemClickListener {
        fun onItemClick(modeloCursos: Cursos)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtCursos: TextView = itemView.findViewById(R.id.txt_cursos_layout)
        val txtEstadoCursos: TextView = itemView.findViewById(R.id.txt_estado_de_cursos_layout)

        init {
            itemView.setOnClickListener {
                if (!itemView.isClickable) return@setOnClickListener
                itemView.isClickable = false

                itemView.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction {
                        itemView.animate().scaleX(1f).scaleY(1f).setDuration(100)

                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
                            val modeloCursos = datos[position]
                            itemClickListener.onItemClick(modeloCursos)
                        }

                        itemView.postDelayed({ itemView.isClickable = true }, 500)
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cursos_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    fun setDatos(datos: List<Cursos>) {
        this.datos = datos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curso = datos[position]

        // Mostrar las materias en líneas separadas
        val materiasTexto = curso.materias.joinToString(separator = "\n")

        holder.txtCursos.text = materiasTexto
        holder.txtEstadoCursos.text = curso.estado
    }
}

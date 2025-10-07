package com.ulatina.proyectoprogramacionv.com.ulatina.proyectoprogramacionv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulatina.proyectoprogramacionv.R

class TarjetaAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<TarjetaAdapter.ViewHolder>() {

    private var datos: List<Tarjetas> = ArrayList()

    interface OnItemClickListener {
        fun onItemClick(modeloTarjeta: Tarjetas)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtNumeroTarjeta: TextView = itemView.findViewById(R.id.txt_tarjeta_layout)
        val txtNombreTitular: TextView = itemView.findViewById(R.id.txt_nombre_tarjeta_layout)

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
                            val modeloTarjeta = datos[position]
                            itemClickListener.onItemClick(modeloTarjeta)
                        }

                        itemView.postDelayed({ itemView.isClickable = true }, 500)
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    fun setDatos(datos: List<Tarjetas>) {
        this.datos = datos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarjeta = datos[position]
        val numero = tarjeta.numero_tarjeta?.toString() ?: ""

        val oculto = if (numero.length >= 4) {
            "**** **** **** " + numero.takeLast(4)
        } else {
            numero
        }

        holder.txtNumeroTarjeta.text = oculto
        holder.txtNombreTitular.text = tarjeta.nombre_tarjeta
    }
}

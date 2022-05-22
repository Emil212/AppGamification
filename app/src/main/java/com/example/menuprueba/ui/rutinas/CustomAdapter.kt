package com.example.menuprueba.ui.rutinas

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.menuprueba.R



class CustomAdapter(private val titulos: MutableList<String>, private val detalles: MutableList<String>, private val vp: MutableList<String>, private val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titulos[i]
        viewHolder.itemDetail.text = detalles[i]

        Glide
            .with(context)
            .load(vp[i])
            //.placeholder(R.drawable.ic_rutinas)//carga el drawable en lo que se ejecuta "load()"
            .fitCenter()
            .centerCrop()
            .into(viewHolder.itemImage)

        viewHolder.apply {
            itemView.setOnClickListener {

                Navigation.findNavController(itemView)
                    .navigate(R.id.action_nav_listaEjerciciosFragment_to_presentacionFragment)
                //para comenzar con la rutina seleccionada
            }
        }
    }

    override fun getItemCount(): Int {
        return titulos.size
    }
    

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detal)
        }
    }
}



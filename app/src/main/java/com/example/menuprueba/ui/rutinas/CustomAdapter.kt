package com.example.menuprueba.ui.rutinas

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.menuprueba.R


class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetail.text = details [i]
        viewHolder.itemImage.setImageResource(images[i])

        viewHolder.apply {
            itemView.setOnClickListener {
                Log.d("Rutina: ","${getName(titles[i])}")
                Navigation.findNavController(itemView).navigate(R.id.action_nav_rutinas_to_presentacionFragment)
                getName(titles[i]) //Función que recupera el nombre, dato que debe de ser enviado a VideosFragment
                //para comenzar con la rutina seleccionada
            }
        }
    }

    override fun getItemCount(): Int {
        return titles.size
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

private fun getName(titles: String): String { //Obtiene el nombre del titulo del cardview (para accerder a cada card)
    val tiles = titles
    return tiles
}


val titles = arrayOf(
    "Rutina 1",
    "Rutina 2",
    "Rutina 3",
    "Rutina 4",
    "Rutina 5"
)
val details = arrayOf(
    "Detalles 1",
    "Detalles 2",
    "Detalles 3",
    "Detalles 4",
    "Detalles 5"
)

val images = arrayOf(
    R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_foreground
)

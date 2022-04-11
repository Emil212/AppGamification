package com.example.menuprueba.ui.rutinas

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.menuprueba.R

class CustomAdapterPresentation(private val nombres: MutableList<String>, private val context: Context) : RecyclerView.Adapter<CustomAdapterPresentation.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_presentation, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = nombres[i]
        Glide
            .with(context)
            .load(vp[i])
            //.placeholder(R.drawable.ic_rutinas)//carga el drawable en lo que se ejecuta "load()"
            .fitCenter()
            .centerCrop()
            .into(viewHolder.itemImage)
    }

    override fun getItemCount(): Int {
        return nombres.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
        }
    }

    val vp = mutableListOf(
        "https://firebasestorage.googleapis.com/v0/b/gamificationapp-2ff7c.appspot.com/o/VP%2FVP_R0001.jpg?alt=media&token=3382882a-9581-4ed7-b575-8b4ebcfa695b",
        "https://firebasestorage.googleapis.com/v0/b/gamificationapp-2ff7c.appspot.com/o/VP%2FVP_R0001.jpg?alt=media&token=3382882a-9581-4ed7-b575-8b4ebcfa695b",
        "https://firebasestorage.googleapis.com/v0/b/gamificationapp-2ff7c.appspot.com/o/VP%2FVP_R0001.jpg?alt=media&token=3382882a-9581-4ed7-b575-8b4ebcfa695b",
        "https://firebasestorage.googleapis.com/v0/b/gamificationapp-2ff7c.appspot.com/o/VP%2FVP_R0001.jpg?alt=media&token=3382882a-9581-4ed7-b575-8b4ebcfa695b"
    )
}
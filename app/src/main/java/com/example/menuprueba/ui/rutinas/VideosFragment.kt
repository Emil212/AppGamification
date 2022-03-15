package com.example.menuprueba.ui.rutinas

import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentVideosBinding
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import io.grpc.Context
import java.lang.ref.Reference


class VideosFragment : Fragment(R.layout.fragment_videos) {

    private lateinit var binding: FragmentVideosBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideosBinding.bind(view)
        seeImage()
    }

    fun seeImage() {
        val Ref = Firebase.storage.reference //Hace referencia a la úbicación del proyecto
        val a = Ref.child("Flexibilidad/saludo-al-sol-1600959006.gif").downloadUrl
        val imageview = binding.imageView
        val ImagesFlex =
            "https://firebasestorage.googleapis.com/v0/b/gamificationapp-2ff7c.appspot.com/o/Flexibilidad%2Fsaludo-al-sol-1600959006.gif?alt=media&token=2101ddb3-380c-4a75-b445-1db9fad3cf5b"
        //                Enlace de descarga (Token de Acceso)
        Glide
            .with(this)
            .load(ImagesFlex)
            .fitCenter()
            .centerCrop()
            .into(imageview)

        Log.d("Prueba de enlace Desc", "$a")
    }
}

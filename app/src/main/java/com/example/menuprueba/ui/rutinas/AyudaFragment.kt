package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.menuprueba.R
import com.example.menuprueba.databinding.ActivityMainBinding
import com.example.menuprueba.databinding.FragmentAyudaBinding

class AyudaFragment : Fragment(R.layout.fragment_ayuda), IOnBackPressed {

    private lateinit var binding: FragmentAyudaBinding
    override fun onBackPressed(): Boolean {
        return true
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAyudaBinding.bind(view)
    }

}
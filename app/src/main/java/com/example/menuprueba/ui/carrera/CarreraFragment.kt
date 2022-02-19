package com.example.menuprueba.ui.carrera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.databinding.FragmentCarreraBinding
import com.example.menuprueba.databinding.FragmentLogrosBinding
import com.example.menuprueba.ui.logros.LogrosViewModel

class CarreraFragment : Fragment() {
    private lateinit var carreraViewModel: CarreraViewModel
    private var _binding : FragmentCarreraBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        carreraViewModel =
            ViewModelProvider(this).get(CarreraViewModel::class.java)

        _binding = FragmentCarreraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCarrera
        val observe = carreraViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.databinding.FragmentRutinasBinding


class RutinasFragment : Fragment() {

    private lateinit var rutinasViewModel: RutinasViewModel
    private var _binding: FragmentRutinasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rutinasViewModel =
            ViewModelProvider(this).get(RutinasViewModel::class.java)

        _binding = FragmentRutinasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRutinas
        rutinasViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
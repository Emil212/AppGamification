package com.example.menuprueba.ui.informe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentInformeActividadesBinding
import com.example.menuprueba.databinding.FragmentLogrosBinding
import com.example.menuprueba.ui.logros.LogrosViewModel

class InformeActividadesFragment : Fragment() {
    private lateinit var informeActividadesViewModel : InformeActividadesViewModel
    private var _binding: FragmentInformeActividadesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        informeActividadesViewModel =
            ViewModelProvider(this).get(InformeActividadesViewModel::class.java)

        _binding = FragmentInformeActividadesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textInforme
        val observe = informeActividadesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
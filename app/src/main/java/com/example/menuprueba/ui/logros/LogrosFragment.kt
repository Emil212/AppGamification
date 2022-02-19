package com.example.menuprueba.ui.logros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.databinding.FragmentRutinasBinding
import com.example.menuprueba.databinding.FragmentRecordatoriosBinding
import com.example.menuprueba.databinding.FragmentCarreraBinding
import com.example.menuprueba.databinding.FragmentInformeActividadesBinding
import com.example.menuprueba.databinding.FragmentLogrosBinding

class LogrosFragment : Fragment() {

    private lateinit var logrosViewModel: LogrosViewModel
    private var _binding: FragmentLogrosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logrosViewModel =
            ViewModelProvider(this).get(LogrosViewModel::class.java)

        _binding = FragmentLogrosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLogros
        logrosViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
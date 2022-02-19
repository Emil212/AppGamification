package com.example.GamificationApp.ui.recordatorios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.GamificationApp.databinding.FragmentRecordatoriosBinding

class RecordatoriosFragment : Fragment() {

    private lateinit var recordatoriosViewModel: RecordatoriosViewModel
    private var _binding: FragmentRecordatoriosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recordatoriosViewModel =
            ViewModelProvider(this).get(RecordatoriosViewModel::class.java)

        _binding = FragmentRecordatoriosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRecordatorios
        recordatoriosViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
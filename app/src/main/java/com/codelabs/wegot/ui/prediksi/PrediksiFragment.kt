package com.codelabs.wegot.ui.prediksi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.codelabs.wegot.databinding.FragmentPrediksiBinding

class PrediksiFragment : Fragment() {

    private var _binding: FragmentPrediksiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(PrediksiViewModel::class.java)

        _binding = FragmentPrediksiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.fabAddPrediksi.setOnClickListener {
            // Handle FAB click here
            Toast.makeText(context, "Add Prediksi Clicked", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
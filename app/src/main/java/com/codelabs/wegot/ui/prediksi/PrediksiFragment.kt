package com.codelabs.wegot.ui.prediksi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.FragmentPrediksiBinding
import com.codelabs.wegot.ui.adapter.SiklusAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrediksiFragment : Fragment() {

    private var _binding: FragmentPrediksiBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PrediksiViewModel by viewModels()
    private val siklusAdapter = SiklusAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrediksiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()

        binding.fabAddPrediksi.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_prediksi_to_navigation_tambah_siklus)
        }

        viewModel.getCarts()
    }

    private fun setupRecyclerView() {
        binding.rvPrediksiSiklus.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = siklusAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.carts.observe(viewLifecycleOwner) { carts ->
            siklusAdapter.setItems(carts)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.codelabs.wegot.ui.prediksi

import android.content.Intent
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
import com.codelabs.wegot.model.local.data.SiklusItem
import com.codelabs.wegot.ui.adapter.SiklusAdapter
import com.codelabs.wegot.ui.notifications.NotificationsActivity
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
        loadDummyData()

        binding.fabAddPrediksi.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_prediksi_to_navigation_tambah_siklus)
        }

        binding.imgNotification.setOnClickListener {
            val intent = Intent(requireContext(), NotificationsActivity::class.java)
            startActivity(intent)
        }

        viewModel.getCarts()
    }

    private fun setupRecyclerView() {
        binding.rvPrediksiSiklus.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = siklusAdapter
        }
    }

    private fun loadDummyData() {
        val dummy = listOf(
            SiklusItem(
                title = "Siklus 1",
                label = "Prediksi Hasil Panen",
                result = "25 kg",
                date = "Estimasi Tanggal Panen : 24 Oktober 2025"
            ),
            SiklusItem(
                title = "Siklus 2",
                label = "Prediksi Hasil Panen",
                result = "18 kg",
                date = "Estimasi Tanggal Panen : 10 November 2025"
            ),
            SiklusItem(
                title = "Siklus 3",
                label = "Prediksi Hasil Panen",
                result = "30 kg",
                date = "Estimasi Tanggal Panen : 02 Desember 2025"
            )
        )
        siklusAdapter.setItems(dummy)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.codelabs.wegot.ui.pencatatan_history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.FragmentPencatatanHistoryBinding
import com.codelabs.wegot.model.local.data.HistoryItem
import com.codelabs.wegot.ui.adapter.HistoryAdapter
import com.codelabs.wegot.ui.notifications.NotificationsActivity

class PencatatanHistoryFragment : Fragment() {

    private var _binding: FragmentPencatatanHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyAdapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(PencatatanHistoryViewModel::class.java)

        _binding = FragmentPencatatanHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.txtSelamatPagi.text = it
        }

        setupLineChart()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadHistoryData()

        binding.fabAddPrediksi.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_pencatatan_history_to_tambahRiwayatActivity)
        }

        binding.imgNotification.setOnClickListener {
            val intent = Intent(requireContext(), NotificationsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.rvPrediksiSiklus.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }
    }

    private fun loadHistoryData() {
        val historyList = listOf(
            HistoryItem("23 Oktober 2025", "100kg", "100kg"),
            HistoryItem("24 Oktober 2025", "80kg", "80kg"),
            HistoryItem("21 Oktober 2025", "70kg", "70kg"),
            HistoryItem("20 Oktober 2025", "70kg", "70kg"),
            HistoryItem("19 Oktober 2025", "70kg", "70kg")
        )
        historyAdapter.setItems(historyList)
    }

    private fun setupLineChart() {
        val wasteData = linkedMapOf(
            "Sen" to 15.0f,
            "Sel" to 20.0f,
            "Rab" to 18.0f,
            "Kam" to 25.0f,
            "Jum" to 30.0f,
            "Sab" to 28.0f,
            "Min" to 22.0f
        )

        binding.lineChartView.apply {
            gradientFillColors = intArrayOf(
                resources.getColor(android.R.color.transparent),
                resources.getColor(android.R.color.transparent)
            )
            animate(wasteData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.codelabs.wegot.ui.prediksi

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.FragmentPrediksiBinding
import com.codelabs.wegot.model.local.data.SiklusItem
import com.codelabs.wegot.model.local.data.UserPreferences
import com.codelabs.wegot.model.remote.network.ApiResponse
import com.codelabs.wegot.ui.adapter.SiklusAdapter
import com.codelabs.wegot.ui.auth.login.LoginActivity
import com.codelabs.wegot.ui.auth.login.LoginViewModel
import com.codelabs.wegot.ui.notifications.NotificationsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.or
import kotlin.text.toInt

@AndroidEntryPoint
class PrediksiFragment : Fragment() {

    private var _binding: FragmentPrediksiBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PrediksiViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
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
        setupClickListeners()
        setupLogout()
        observeLogout()
    }

    private fun observeLogout() {
        loginViewModel.logoutResult.observe(viewLifecycleOwner) { res ->
            when (res) {
                is ApiResponse.Empty -> {
                    binding.imgLogout.isEnabled = false
                }
                is ApiResponse.Success -> {
                    val intent = Intent(requireContext(), LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    requireActivity().finish()
                }
                is ApiResponse.Error -> {
                    binding.imgLogout.isEnabled = true
                    Toast.makeText(requireContext(), res.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupLogout() {
        binding.imgLogout.isClickable = true
        binding.imgLogout.setOnClickListener {
            val confirmDialog = AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Yakin ingin logout?")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Ya") { _, _ ->
                    val progressBar = ProgressBar(requireContext()).apply {
                        isIndeterminate = true
                        val pad = (16 * resources.displayMetrics.density).toInt()
                        setPadding(pad, pad, pad, pad)
                    }
                    val loadingDialog = AlertDialog.Builder(requireContext())
                        .setView(progressBar)
                        .setCancelable(false)
                        .create()
                    loadingDialog.show()

                    lifecycleScope.launch {
                        try {
                            val prefs = UserPreferences(requireContext())
                            prefs.clear()
                        } catch (e: Exception) {
                        } finally {
                            loadingDialog.dismiss()
                            val intent = Intent(requireContext(), LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    }
                }
                .create()
            confirmDialog.show()
        }
    }

    private fun setupClickListeners() {
        binding.fabAddPrediksi.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_prediksi_to_navigation_tambah_siklus)
        }

        binding.imgNotification.setOnClickListener {
            val intent = Intent(requireContext(), NotificationsActivity::class.java)
            startActivity(intent)
        }
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
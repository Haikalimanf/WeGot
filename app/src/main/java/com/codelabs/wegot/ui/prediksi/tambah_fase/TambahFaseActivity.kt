package com.codelabs.wegot.ui.prediksi.tambah_fase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.ActivityTambahFaseBinding
import com.codelabs.wegot.ui.prediksi.hasil_prediksi.HasilPrediksiActivity
import com.codelabs.wegot.utils.DateTimeUtils

class TambahFaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahFaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTambahFaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnSimpan.setOnClickListener {
            startActivity(Intent(this, HasilPrediksiActivity::class.java))
        }

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.edtTanggalPembesaran.setEndIconOnClickListener {
            showDatePickerDialog()
        }
        binding.inputTanggalPembesaran.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        DateTimeUtils.showDatePicker(this) { selectedDate ->
            binding.inputTanggalPembesaran.setText(selectedDate)
        }
    }
}
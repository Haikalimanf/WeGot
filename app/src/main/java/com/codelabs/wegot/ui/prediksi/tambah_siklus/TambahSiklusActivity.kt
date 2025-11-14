package com.codelabs.wegot.ui.prediksi.tambah_siklus

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codelabs.wegot.R
import com.codelabs.wegot.ui.MainActivity
import com.codelabs.wegot.ui.prediksi.detail_siklus.DetailSiklusActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class TambahSiklusActivity : AppCompatActivity() {

    private lateinit var inputTanggal: TextInputEditText
    private lateinit var inputJumlahTelur: TextInputEditText
    private lateinit var mediaTelur: AutoCompleteTextView
    private lateinit var btnSimpanSiklus: Button
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_siklus)
        supportActionBar?.hide()
        inputTanggal = findViewById(R.id.inputTanggal)
        inputJumlahTelur = findViewById(R.id.inputJumlahTelur)
        mediaTelur = findViewById(R.id.inputMediaTelur)
        btnSimpanSiklus = findViewById(R.id.btnSimpanSiklus)
        toolbar = findViewById(R.id.topAppBar)

        setupToolbar()
        setupDatePicker()
        setupDropdownMedia()
        setupSaveButton()
    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun setupDatePicker() {
        inputTanggal.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, day ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, day)

                    val displayFormat = java.text.SimpleDateFormat("dd MMMM yyyy", java.util.Locale("id", "ID"))
                    inputTanggal.setText(displayFormat.format(selectedDate.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }
    }


    private fun setupDropdownMedia() {
        val options = listOf("Serbuk Kelapa", "Tanah", "Pasir", "Campuran")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        mediaTelur.setAdapter(adapter)
    }

    private fun setupSaveButton() {
        btnSimpanSiklus.setOnClickListener {
            val tanggal = inputTanggal.text.toString()
            val jumlahTelur = inputJumlahTelur.text.toString()
            val media = mediaTelur.text.toString()
            if (tanggal.isEmpty() || jumlahTelur.isEmpty() || media.isEmpty()) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, DetailSiklusActivity::class.java)
                intent.putExtra("EXTRA_TANGGAL", tanggal)
                intent.putExtra("EXTRA_JUMLAH", jumlahTelur)
                intent.putExtra("EXTRA_MEDIA", media)
                startActivity(intent)
            }
        }
    }
}
package com.codelabs.wegot.ui.prediksi.tambah_siklus

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.ActivityMainBinding
import com.codelabs.wegot.databinding.ActivityTambahSiklusBinding
import com.codelabs.wegot.ui.prediksi.detail_siklus.DetailSiklusActivity
import com.codelabs.wegot.utils.DateTimeUtils
import com.codelabs.wegot.utils.enumClass.MediaTelurType
import kotlin.toString

class TambahSiklusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahSiklusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTambahSiklusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val items = MediaTelurType.values().map { it.toString() }.toTypedArray()
        val arrayAdapter = ArrayAdapter(this, R.layout.item_dropdown_media_telur, items)
        (binding.dropDownMediaTelur.editText as? AutoCompleteTextView)?.setAdapter(arrayAdapter)

        binding.btnSimpanSiklus.setOnClickListener {
            startActivity(Intent(this, DetailSiklusActivity::class.java))
        }
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.inputMediaTelur.setOnItemClickListener { adapterView, _, position, _ ->
            val selectedItem = adapterView.getItemAtPosition(position).toString()
            binding.dropDownMediaTelur.isHintEnabled = false
        }
        binding.edtTanggal.setEndIconOnClickListener {
            showDatePickerDialog()
        }
        binding.inputTanggal.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        DateTimeUtils.showDatePicker(this) { selectedDate ->
            binding.inputTanggal.setText(selectedDate)
        }
    }

}
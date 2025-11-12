package com.codelabs.wegot.ui.prediksi.tambah_fase

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codelabs.wegot.R
import com.codelabs.wegot.ui.MainActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TambahFaseActivity : AppCompatActivity() {

    private lateinit var inputTanggal: TextInputEditText
    private lateinit var btnSimpan: Button
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_fase)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputTanggal = findViewById(R.id.inputTanggalPembesaran)
        btnSimpan = findViewById(R.id.btnSimpanFase)
        toolbar = findViewById(R.id.topAppBar)

        setupToolbar()
        setupDatePicker()
        setupSaveButton()
    }

    //ini agar semua data terhapus dan kembali ke home karena pake dummy crud jadi aga bingung anjay biar dinamis terus
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

                    val displayFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
                    inputTanggal.setText(displayFormat.format(selectedDate.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }
    }

    private fun setupSaveButton() {
        btnSimpan.setOnClickListener {
            val tanggal = inputTanggal.text.toString()
            if (tanggal.isEmpty()) {
                Toast.makeText(this, "Harap isi tanggal pembesaran", Toast.LENGTH_SHORT).show()
            } else {
                val resultIntent = Intent()
                resultIntent.putExtra("EXTRA_TANGGAL_PEMBESARAN", tanggal)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
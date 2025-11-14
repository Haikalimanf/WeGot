package com.codelabs.wegot.ui.prediksi.detail_siklus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.wegot.R
import com.codelabs.wegot.ui.MainActivity
import com.codelabs.wegot.ui.adapter.DetailSiklusAdapter
import com.codelabs.wegot.ui.prediksi.PrediksiFragment
import com.codelabs.wegot.ui.prediksi.hasil_prediksi.HasilPrediksiActivity
import com.codelabs.wegot.ui.prediksi.tambah_fase.TambahFaseActivity
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailSiklusActivity : AppCompatActivity() {

    private lateinit var rvSiklus: RecyclerView
    private lateinit var siklusAdapter: DetailSiklusAdapter
    private val siklusList = mutableListOf<SiklusItem>()
    private lateinit var tambahFaseLauncher: ActivityResultLauncher<Intent>
    private val displayFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_siklus)

        supportActionBar?.hide()
        toolbar = findViewById(R.id.topAppBar)
        tambahFaseLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val tanggalPembesaranBaru = data?.getStringExtra("EXTRA_TANGGAL_PEMBESARAN")

                if (tanggalPembesaranBaru != null) {
                    updateSiklusList(tanggalPembesaranBaru)
                }
            }
        }
        setupToolbar()
        val tanggalAwal = intent.getStringExtra("EXTRA_TANGGAL") ?: "Tanggal tidak ada"
        val jumlah = intent.getStringExtra("EXTRA_JUMLAH") ?: "0"
        val media = intent.getStringExtra("EXTRA_MEDIA") ?: "Media tidak ada"
        Log.d("DetailSiklusActivity", "Tanggal: $tanggalAwal, Jumlah: $jumlah, Media: $media")

        rvSiklus = findViewById(R.id.rvSiklus)
        rvSiklus.layoutManager = LinearLayoutManager(this)

        siklusList.addAll(createSiklusData(tanggalAwal, jumlah, media))

        siklusAdapter = DetailSiklusAdapter(siklusList) { siklusItem ->
            when (siklusItem.title) {
                "Pembesaran" -> {
                    val intent = Intent(this, TambahFaseActivity::class.java)
                    intent.putExtra("EXTRA_TITLE", siklusItem.title)
                    tambahFaseLauncher.launch(intent)
                }
                "Panen" -> {
                    val intent = Intent(this, HasilPrediksiActivity::class.java)
                    intent.putExtra("EXTRA_TANGGAL_PANEN", siklusItem.date)
                    startActivity(intent)
                }
            }
        }


        rvSiklus.adapter = siklusAdapter

    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun updateSiklusList(tanggalPembesaranBaru: String) {
        val indexPembesaran = siklusList.indexOfFirst { it.title == "Pembesaran" }

        if (indexPembesaran != -1) {
            val itemLama = siklusList[indexPembesaran]
            siklusList[indexPembesaran] = itemLama.copy(
                date = tanggalPembesaranBaru,
                description = "Telur menetas dipindahkan ke wadah pembesaran",
                isClickable = false
            )
        }

        try {
            val calendar = Calendar.getInstance()
            val date = displayFormat.parse(tanggalPembesaranBaru)
            if (date != null) {
                calendar.time = date
            }
            calendar.add(Calendar.DAY_OF_YEAR, 7)

            val tanggalPanen = displayFormat.format(calendar.time)

            siklusList.add(
                SiklusItem(
                    title = "Panen",
                    date = tanggalPanen,
                    description = "Telur siap dipanen",
                    isClickable = true
                )
            )
        } catch (e: Exception) {
            Log.e("DetailSiklus", "Gagal parse tanggal untuk Panen: $tanggalPembesaranBaru", e)
        }
        siklusAdapter.notifyDataSetChanged()
    }
    private fun createSiklusData(
        tanggalAwal: String,
        jumlah: String,
        media: String
    ): List<SiklusItem> {

        val list = mutableListOf<SiklusItem>()
        val parseFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        val calendar = Calendar.getInstance()

        try {
            val date = parseFormat.parse(tanggalAwal)
            if (date != null) {
                calendar.time = date
                val tanggalAwalFormatted = displayFormat.format(date)

                list.add(
                    SiklusItem(
                        title = "Penetasan",
                        date = tanggalAwalFormatted,
                        description = "Telur dipindahkan ke wadah penetasan"
                    )
                )
            }
        } catch (e: Exception) {
            Log.e("DetailSiklus", "Format tanggal salah: $tanggalAwal")
        }

        list.add(
            SiklusItem(
                title = "Pembesaran",
                date = "",
                description = "Masukkan detail pembesaran",
                isClickable = true
            )
        )
        return list
    }
}
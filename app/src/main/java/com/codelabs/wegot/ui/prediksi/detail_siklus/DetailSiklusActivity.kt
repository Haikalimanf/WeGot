package com.codelabs.wegot.ui.prediksi.detail_siklus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.ActivityDetailSiklusBinding
import com.codelabs.wegot.databinding.ActivityTambahSiklusBinding
import com.codelabs.wegot.ui.prediksi.tambah_fase.TambahFaseActivity

class DetailSiklusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSiklusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailSiklusBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.arrowIcon.setOnClickListener {
            startActivity(Intent(this, TambahFaseActivity::class.java))
        }
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
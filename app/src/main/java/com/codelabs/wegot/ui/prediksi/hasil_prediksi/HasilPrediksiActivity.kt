package com.codelabs.wegot.ui.prediksi.hasil_prediksi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.ActivityHasilPrediksiBinding
import com.codelabs.wegot.databinding.ActivityTambahFaseBinding

class HasilPrediksiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilPrediksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHasilPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}
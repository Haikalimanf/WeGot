package com.codelabs.wegot.ui.auth.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.ActivityRegisterBinding
import com.codelabs.wegot.model.remote.network.ApiResponse
import com.codelabs.wegot.ui.MainActivity
import com.codelabs.wegot.ui.auth.login.LoginActivity
import com.codelabs.wegot.ui.auth.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val loginViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setTextToLogin()
        setupClickListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        loginViewModel.isLoading.observe(this) { isLoading ->
            binding.btnDaftar.isEnabled = !isLoading
            binding.btnDaftar.text = if (isLoading) "Loading..." else "Masuk"
        }

        loginViewModel.registerResult.observe(this) { result ->
            when (result) {
                is ApiResponse.Success -> {
                    Toast.makeText(this, "Daftar berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                is ApiResponse.Error -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ApiResponse.Empty -> {
                    // Loading state handled above
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnDaftar.setOnClickListener {
            val username = binding.inputUsername.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()
            val rw = binding.inputNomorRW.text.toString().trim()

            if (validateInput(username, password, rw)) {
                loginViewModel.register(username, password, rw)
            }
        }
    }

    private fun setTextToLogin() {
        val fullText = "Sudah Punya Akun? Masuk"
        val target = "Masuk"
        val start = fullText.indexOf(target)
        val end = start + target.length

        val spannable = SpannableString(fullText)
        val clickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // navigate to LoginActivity
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#1E88E5")
                ds.isUnderlineText = false
            }
        }

        if (start >= 0) {
            spannable.setSpan(clickable, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.tvAlreadyAccount.text = spannable
            binding.tvAlreadyAccount.movementMethod = LinkMovementMethod.getInstance()
            binding.tvAlreadyAccount.highlightColor = Color.TRANSPARENT
        }
    }

    private fun validateInput(username: String, password: String, rw : String): Boolean {
        return when {
            username.isEmpty() -> {
                binding.edtUsername.error = "Username tidak boleh kosong"
                false
            }
            password.isEmpty() -> {
                binding.edtPassword.error = "Password tidak boleh kosong"
                false
            }
            rw.isEmpty() -> {
                binding.edtRukunWarga.error = "Password tidak boleh kosong"
                false
            }
            else -> true
        }
    }
}
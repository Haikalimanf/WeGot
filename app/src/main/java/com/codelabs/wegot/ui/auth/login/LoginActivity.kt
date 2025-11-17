package com.codelabs.wegot.ui.auth.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.ActivityLoginBinding
import com.codelabs.wegot.ui.auth.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTextToLogin()
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
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
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
}
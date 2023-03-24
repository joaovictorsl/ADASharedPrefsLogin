package me.joaovictorsl.practicing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import me.joaovictorsl.practicing.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val etEmail by lazy { binding.etEmail }
    private val etPassword by lazy { binding.etPassword }
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.loginBtn.setOnClickListener {
            if (isValidCredentials()) {
                prefs.default_user = true
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else
                Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_SHORT).show()
        }

        binding.goToSignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidCredentials(): Boolean {
        return etEmail.text.toString() == prefs.email && etPassword.text.toString().toInt() == prefs.pass
    }
}
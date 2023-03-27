package me.joaovictorsl.practicing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.joaovictorsl.practicing.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val etEmail by lazy { binding.etEmail }
    private val etPassword by lazy { binding.etPassword }
    private val etUsername by lazy { binding.etUsername }
    private val etAge by lazy { binding.etAge }
    private val etCPF by lazy { binding.etCPF }
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        allowUserIfLogged()
        setClickListeners()
    }

    private fun allowUserIfLogged() {
        if (prefs.default_user) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setClickListeners() {
        binding.signUpBtn.setOnClickListener {
            prefs.email = etEmail.text.toString()
            prefs.pass = etPassword.text.toString().toInt()
            prefs.name = etUsername.text.toString()
            prefs.age = etAge.text.toString().toInt()
            prefs.cpf = etCPF.text.toString()
            val user = User(id = prefs.email.toString(), filePath = "${prefs.email.toString()}.txt")
            val userDao = AppDatabase.create(this).userDao()

            CoroutineScope(Dispatchers.IO).launch {
                userDao.insertUser(user)
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
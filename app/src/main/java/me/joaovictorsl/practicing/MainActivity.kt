package me.joaovictorsl.practicing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.joaovictorsl.practicing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tvAge by lazy { binding.tvAge }
    private val tvEmail by lazy { binding.tvEmail }
    private val tvUsername by lazy { binding.tvUsername }
    private val tvCPF by lazy { binding.tvCPF }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListeners()
        setUserInfo()
    }

    private fun setUserInfo() {
        tvAge.text = prefs.age.toString()
        tvEmail.text = prefs.email
        tvUsername.text = prefs.name
        tvCPF.text = prefs.cpf
    }

    private fun setClickListeners() {
        binding.logoutBtn.setOnClickListener {
            prefs.logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.myNotesBtn.setOnClickListener{
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
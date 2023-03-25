package me.joaovictorsl.practicing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.joaovictorsl.practicing.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity()  {
    private val etNotes by lazy { binding.etNotes }
    private val binding by lazy { ActivityNotesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        etNotes.setText(openTextArchive("myNotes.txt",applicationContext))
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.returnBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.saveNotesBtn.setOnClickListener {
            val notes = etNotes.text.toString()
            writeTextArchive(notes,applicationContext)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
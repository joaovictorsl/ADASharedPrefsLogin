package me.joaovictorsl.practicing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import me.joaovictorsl.practicing.databinding.ActivityNotesBinding
import java.util.concurrent.Executors

class NotesActivity : AppCompatActivity()  {
    private val etNotes by lazy { binding.etNotes }
    private val binding by lazy { ActivityNotesBinding.inflate(layoutInflater) }
    private lateinit var userDao: UserDao
    private var user: User? = null
    private var filePath: String? = null
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userDao = AppDatabase.create(this).userDao()

        executor.execute {
            user = userDao.getUserById(prefs.email)
            filePath = user?.filePath

            handler.post {
                etNotes.setText(openTextArchive(filePath, applicationContext))
            }
        }

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

            executor.execute {
                writeTextArchive(notes, filePath, applicationContext)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
package id.co.sistema.catatanlhj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import id.co.sistema.catatanlhj.databinding.ActivityMainPageBinding

class MainPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        butNavLogin()

    }

    private fun butNavLogin(){

        binding.apply {
            btLogin.setOnClickListener {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }

            btRegister.setOnClickListener {
                startActivity(Intent(applicationContext, RegisterActivity::class.java))
            }
        }
    }
}
package id.co.sistema.catatanlhj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.co.sistema.catatanlhj.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        login()
    }


    private fun login(){
        binding.let {
            with(binding){
                btLogin.setOnClickListener {startActivity(Intent(applicationContext , MainMenuActivity::class.java)) }
            }
        }
    }

}
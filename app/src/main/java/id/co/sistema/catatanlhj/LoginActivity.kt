package id.co.sistema.catatanlhj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.co.sistema.catatanlhj.ROOM.UserDatabase
import id.co.sistema.catatanlhj.ROOM.UserRepository
import id.co.sistema.catatanlhj.ROOM.UserViewModel
import id.co.sistema.catatanlhj.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        login()
    }


    private fun login(){
        binding.let {
            with(binding){
                btLogin.setOnClickListener {
                    val username = etUsernameLogin.text.toString()
                    val password = etPasswordLogin.text.toString()

                    val user = viewModel.login(username,password)

                    if (user != null){
                        startActivity(Intent(applicationContext , MainMenuActivity::class.java))
                        Toast.makeText(applicationContext , "User Found!", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext , "Wrong Password/Username !", Toast.LENGTH_SHORT).show()
                    } }
                btRegister.setOnClickListener {
                    startActivity(Intent(applicationContext, RegisterActivity::class.java))
                    finish()}
            }
        }
    }

}
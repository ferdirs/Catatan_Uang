package id.co.sistema.catatanlhj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import id.co.sistema.catatanlhj.ROOM.UserDatabase
import id.co.sistema.catatanlhj.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        login()

    }


    private suspend fun authLogin(){
        if (binding.etUsernameLogin.text.isNotEmpty() && binding.etPasswordLogin.text.isNotEmpty()){
            val userDao = UserDatabase.getDatabase(applicationContext).userDao()
            val user = userDao.login(binding.etUsernameLogin.text.toString(), binding.etPasswordLogin.text.toString())
            runOnUiThread {
                if (user != null){
                    startActivity(Intent(applicationContext , MainMenuActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(applicationContext, "Password/Username anda salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun inputCheck(userName: String, password: String): Boolean{

        return !(TextUtils.isEmpty(userName) && TextUtils.isEmpty(password))

    }

    private fun login(){
        binding.let {
            with(binding){
                btLogin.setOnClickListener {
                    GlobalScope.launch(Dispatchers.IO) {
                        authLogin()
                    }
                }
                btRegister.setOnClickListener { startActivity(Intent(applicationContext, RegisterActivity::class.java)) }
            }
        }
    }

}
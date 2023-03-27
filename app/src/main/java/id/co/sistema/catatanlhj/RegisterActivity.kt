package id.co.sistema.catatanlhj

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.co.sistema.catatanlhj.ROOM.User
import id.co.sistema.catatanlhj.ROOM.UserViewModel
import id.co.sistema.catatanlhj.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        addUser()
    }

    private fun addUser(){
        binding.let {
            with(binding){
                btRegister2.setOnClickListener {
                    insertDataToDatabase()
                }
                btLogin2.setOnClickListener {
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                }
            }
        }
    }

    private fun insertDataToDatabase(){
        val userName = binding.etUsernameRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()
        val password2 = binding.etPassword2Register.text.toString()

        if (inputCheck(userName, password, password2)){
            val user = User(0, userName, password)
            mUserViewModel.addUser(user)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, MainMenuActivity::class.java))
        }else{
            Toast.makeText(this, "Please fill the field!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(userName: String, password: String, password2: String): Boolean{

        return !(TextUtils.isEmpty(userName) && TextUtils.isEmpty(password) && TextUtils.isEmpty(password2))

    }
}
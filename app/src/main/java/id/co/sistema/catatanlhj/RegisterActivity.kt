package id.co.sistema.catatanlhj

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import id.co.sistema.catatanlhj.ROOM.User
import id.co.sistema.catatanlhj.ROOM.UserViewModel
import id.co.sistema.catatanlhj.databinding.ActivityRegisterBinding
import java.util.concurrent.Executor

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

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
                    biometricExec()
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

    private fun biometricExec(){
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this@RegisterActivity, executor, object :BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(applicationContext, "Authentication Failed $errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                insertDataToDatabase()
                Toast.makeText(applicationContext, "Authentication success", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT).show()

            }
        })
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("BiometricAuth")
            .setSubtitle("Login using fingerprint")
            .setNegativeButtonText("Use app password instead")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}
package id.co.sistema.catatanlhj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import java.time.Instant

class SplashScreenActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        GlobalScope.launch {
            nextScreen()
        }

    }

    private suspend fun nextScreen(){
        delay(3000L)
        startActivity(Intent(this , MainPageActivity::class.java))
    }
}
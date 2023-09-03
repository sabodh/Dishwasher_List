package com.online.partnerships

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.online.partnerships.presentation.ProductActivity

/**
* Splash screen
*/
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Intent(this, ProductActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}
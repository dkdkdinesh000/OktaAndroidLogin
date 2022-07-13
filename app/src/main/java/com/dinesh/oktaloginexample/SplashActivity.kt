package com.dinesh.oktaloginexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dinesh.oktaloginexample.R

class SplashActivity : AppCompatActivity() {
    private val oktaManager: OktaManager by lazy { (application as OktaLoginApplication).oktaManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (oktaManager.isAuthenticated()) {

            navigateToHome()
        } else {
            navigateToLogin()
        }


    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
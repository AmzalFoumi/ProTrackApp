package com.amzal.protrackapp


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.amzal.protrackapp.onboarding.OnboardingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        // ✅ Show theme-based splash
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // ✅ Keep splash visible a bit longer (2s delay)
        splashScreen.setKeepOnScreenCondition { true }
        window.decorView.postDelayed({
            // SharedPreferences check (replace with real logic)
            val isFirstTime = true
            if (isFirstTime) {
                startActivity(Intent(this, OnboardingActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 1000)
    }
}
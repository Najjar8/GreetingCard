package com.example.greetingcard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.greetingcard.activities.SplashScreen
import com.example.greetingcard.ui.theme.GreetingCardTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install system splash screen (Android 12+). Keeps the OS splash
        // visible until the first Compose frame is ready.
        installSplashScreen()

        super.onCreate(savedInstanceState)

        // Draw behind system bars for true full-bleed immersion
        enableEdgeToEdge()

        setContent {
            GreetingCardTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SplashScreen(
                        onSplashFinished = ::navigateToMain
                    )
                }
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish() // Remove splash from back stack
    }
}
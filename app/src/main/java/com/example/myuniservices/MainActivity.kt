package com.example.myuniservices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.myuniservices.navigation.AppNavigation
import com.example.myuniservices.ui.theme.MyUniServicesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyUniServicesTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

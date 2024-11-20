package com.example.adivinarpalabra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.adivinarpalabra.ui.theme.AdivinarPalabraTheme
import com.example.adivinarpalabra.view.MyApp
import com.example.adivinarpalabra.viewmodel.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModel()
        enableEdgeToEdge()
        setContent {
            AdivinarPalabraTheme {
                MyApp(viewModel)
            }
        }
    }
}

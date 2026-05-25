package com.teamstandup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.teamstandup.navigation.AppNavigation
import com.teamstandup.ui.theme.TeamStandupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TeamStandupTheme {
                AppNavigation()
            }
        }
    }
}

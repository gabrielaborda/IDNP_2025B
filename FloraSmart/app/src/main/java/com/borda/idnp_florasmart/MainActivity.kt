package com.borda.idnp_florasmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.borda.idnp_florasmart.data.datastore.ThemePreferences
import com.borda.idnp_florasmart.navigation.AppNavigation
import com.borda.idnp_florasmart.ui.theme.IDNP_FloraSmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = this
            val themePreferences = remember { ThemePreferences(context) }
            val isDarkMode by themePreferences.isDarkMode.collectAsState(initial = false)
            val navController = rememberNavController()

            IDNP_FloraSmartTheme(darkTheme = isDarkMode) {
                AppNavigation(
                    navController = navController,
                    themePreferences = themePreferences
                )
            }
        }
    }
}
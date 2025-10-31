package com.borda.idnp_florasmart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.borda.idnp_florasmart.ui.screens.plantlist.PlantListScreen
import com.borda.idnp_florasmart.ui.screens.settings.SettingsScreen
import com.borda.idnp_florasmart.data.datastore.ThemePreferences
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    navController: NavHostController,
    themePreferences: ThemePreferences
) {
    val isDarkMode by themePreferences.isDarkMode.collectAsState(initial = false)
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = "plant_list"
    ) {
        composable("plant_list") {
            PlantListScreen(
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }
        composable("settings") {
            SettingsScreen(
                themePreferences = themePreferences,
                isDarkMode = isDarkMode,
                onToggleTheme = {
                    scope.launch {
                        themePreferences.saveThemePreference(!isDarkMode)
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
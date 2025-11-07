package com.borda.idnp_florasmart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.borda.idnp_florasmart.data.datastore.ThemePreferences
import com.borda.idnp_florasmart.data.repository.UserPlantRepository
import com.borda.idnp_florasmart.ui.screens.plantlist.PlantListScreen
import com.borda.idnp_florasmart.ui.screens.settings.SettingsScreen
import com.borda.idnp_florasmart.ui.screens.plantregister.UserPlantScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    navController: NavHostController,
    themePreferences: ThemePreferences,
    repository: UserPlantRepository // ✅ nuevo parámetro
) {
    val isDarkMode by themePreferences.isDarkMode.collectAsState(initial = false)
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = "plant_list"
    ) {
        // 🌿 Lista general de plantas (catálogo)
        composable("plant_list") {
            PlantListScreen(
                onNavigateToSettings = { navController.navigate("settings") },
                onNavigateToUserPlants = { navController.navigate("user_plants") } // ✅ nuevo destino
            )
        }

        // ⚙️ Configuración (tema claro/oscuro)
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

        // 🌱 Pantalla del huerto del usuario (registro + lista Room)
        composable("user_plants") {
            UserPlantScreen(
                repository = repository,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

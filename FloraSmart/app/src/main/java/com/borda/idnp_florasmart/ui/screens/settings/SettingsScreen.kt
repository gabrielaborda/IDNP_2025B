package com.borda.idnp_florasmart.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.borda.idnp_florasmart.data.datastore.ThemePreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    themePreferences: ThemePreferences,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("⚙️ Configuración") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Tema actual: ${if (isDarkMode) "Oscuro 🌙" else "Claro ☀️"}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onToggleTheme) {
                Text(if (isDarkMode) "Cambiar a modo claro" else "Cambiar a modo oscuro")
            }
        }
    }
}
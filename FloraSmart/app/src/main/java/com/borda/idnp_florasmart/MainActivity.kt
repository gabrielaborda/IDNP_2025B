package com.borda.idnp_florasmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.borda.idnp_florasmart.ui.screens.plantlist.PlantListScreen
import com.borda.idnp_florasmart.ui.theme.IDNP_FloraSmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IDNP_FloraSmartTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { FloraSmartAppBar() }
                ) { innerPadding ->
                    PlantListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloraSmartAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "🌿 FloraSmart - Lista de Plantas",
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

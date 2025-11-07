package com.borda.idnp_florasmart.ui.screens.plantlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.borda.idnp_florasmart.data.PlantData
import com.borda.idnp_florasmart.model.Plant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantListScreen(
    onNavigateToSettings: () -> Unit = {},
    onNavigateToUserPlants: () -> Unit = {} // ✅ nuevo parámetro
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🌿 FloraSmart - Lista de Plantas") },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                items(PlantData.plantList) { plant ->
                    PlantCard(plant)
                }
            }

            // ✅ Botón para navegar a la pantalla de registro de plantas
            Button(
                onClick = onNavigateToUserPlants,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("🌱 Ir a Mi Huerto")
            }
        }
    }
}

@Composable
fun PlantCard(plant: Plant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(plant.imageUrl),
                contentDescription = plant.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = plant.name, style = MaterialTheme.typography.titleMedium)
                Text(text = plant.species, style = MaterialTheme.typography.bodyMedium)
                Text(text = plant.growthStage, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

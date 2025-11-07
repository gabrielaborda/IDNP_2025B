package com.borda.idnp_florasmart.ui.screens.plantregister

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.borda.idnp_florasmart.data.repository.UserPlantRepository
import com.borda.idnp_florasmart.data.room.UserPlantEntity
import com.borda.idnp_florasmart.viewmodel.UserPlantViewModel
import com.borda.idnp_florasmart.viewmodel.UserPlantViewModelFactory

@Composable
fun UserPlantScreen(
    repository: UserPlantRepository, // ✅ cambiamos el parámetro
    onNavigateBack: () -> Unit = {}   // ✅ agregado para volver
) {
    val viewModel: UserPlantViewModel = viewModel(
        factory = UserPlantViewModelFactory(repository)
    )

    val plants by viewModel.plantsState.collectAsState()

    var name by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Registrar planta", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = species,
            onValueChange = { species = it },
            label = { Text("Especie") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notas (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        Row {
            Button(onClick = {
                viewModel.addPlant(name, species, notes.ifBlank { null })
                name = ""
                species = ""
                notes = ""
            }) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(onClick = { viewModel.clearAll() }) {
                Text("Eliminar todo")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Plantas registradas", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(plants) { plant: UserPlantEntity ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(plant.name, style = MaterialTheme.typography.titleMedium)
                        Text(plant.species, style = MaterialTheme.typography.bodyMedium)
                        plant.notes?.let {
                            Spacer(Modifier.height(4.dp))
                            Text(it, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // ✅ Botón para volver atrás
        OutlinedButton(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
            Text("⬅ Volver")
        }
    }
}

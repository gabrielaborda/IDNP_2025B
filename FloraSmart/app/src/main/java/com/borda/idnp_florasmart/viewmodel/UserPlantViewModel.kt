package com.borda.idnp_florasmart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borda.idnp_florasmart.data.repository.UserPlantRepository
import com.borda.idnp_florasmart.data.room.UserPlantEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserPlantViewModel(private val repo: UserPlantRepository) : ViewModel() {

    // Flow expuesto como StateFlow para Compose
    val plantsState: StateFlow<List<UserPlantEntity>> =
        repo.getAllPlants()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addPlant(name: String, species: String, notes: String?) {
        if (name.isBlank() || species.isBlank()) return
        viewModelScope.launch {
            val plant = UserPlantEntity(
                name = name.trim(),
                species = species.trim(),
                notes = notes?.trim()
            )
            repo.insertPlant(plant)
        }
    }

    fun clearAll() {
        viewModelScope.launch { repo.deleteAll() }
    }
}

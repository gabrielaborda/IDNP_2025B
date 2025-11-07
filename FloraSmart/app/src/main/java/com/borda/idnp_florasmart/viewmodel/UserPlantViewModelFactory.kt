package com.borda.idnp_florasmart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borda.idnp_florasmart.data.repository.UserPlantRepository

class UserPlantViewModelFactory(private val repo: UserPlantRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserPlantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserPlantViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

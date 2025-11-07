package com.borda.idnp_florasmart.data.repository

import com.borda.idnp_florasmart.data.room.UserPlantDao
import com.borda.idnp_florasmart.data.room.UserPlantEntity
import kotlinx.coroutines.flow.Flow

class UserPlantRepository(private val dao: UserPlantDao) {
    fun getAllPlants(): Flow<List<UserPlantEntity>> = dao.getAllPlants()
    suspend fun insertPlant(plant: UserPlantEntity) = dao.insert(plant)
    suspend fun deleteAll() = dao.deleteAll()
}
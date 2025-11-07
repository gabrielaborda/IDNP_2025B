package com.borda.idnp_florasmart.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPlantDao {
    @Insert
    suspend fun insert(plant: UserPlantEntity): Long

    @Query("SELECT * FROM user_plants ORDER BY id DESC")
    fun getAllPlants(): Flow<List<UserPlantEntity>>

    @Query("DELETE FROM user_plants")
    suspend fun deleteAll()
}

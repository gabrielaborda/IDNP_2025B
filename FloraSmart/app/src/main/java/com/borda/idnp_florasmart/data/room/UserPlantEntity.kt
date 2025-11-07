package com.borda.idnp_florasmart.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_plants")
data class UserPlantEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val species: String,
    val notes: String? = null,
    val photoUri: String? = null
)

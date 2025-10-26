package com.borda.idnp_florasmart.model

data class Plant(
    val id: Int,
    val name: String,
    val species: String,
    val growthStage: String,
    val imageUrl: String
)

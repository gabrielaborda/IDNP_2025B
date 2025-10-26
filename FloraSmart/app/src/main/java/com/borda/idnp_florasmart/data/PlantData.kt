package com.borda.idnp_florasmart.data

import com.borda.idnp_florasmart.model.Plant

object PlantData {
    val plantList = List(20) { index ->
        Plant(
            id = index + 1,
            name = "Planta ${index + 1}",
            species = "Especie ${index + 1}",
            growthStage = when (index % 4) {
                0 -> "Germinación"
                1 -> "Crecimiento"
                2 -> "Floración"
                else -> "Cosecha"
            },
            imageUrl = "https://picsum.photos/200?random=${index + 1}"
        )
    }
}

package com.example.ffxivproject.data.api.armour

import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.MountEntity
import com.example.ffxivproject.data.api.mount.MountApiModel
import com.google.gson.Gson

// Modelo ya mapeado y bonito que mostraremos
data class ArmourApiModel(
    val id: Int,
    val name: String,
    val owned: String,
    val icon: String,
    val type: String,
    val text: String
)

data class Recursos(
    val type: String,
    val text: String
)

data class ArmourListApiModel(
    val list: List<ArmourApiModel>
)

data class ArmourListResponse(
    val results: List<ArmourListItem>
)

data class ArmourListItem(
    val id: Int
)

// Respuesta del detalle
data class ArmourDetailResponse(
    val id: Int,
    val name: String,
    val owned: String,
    val icon: String,
    val sources: List<Recursos>

) {
    // Función para pasar de la respuesta a lo bonito
    fun asApiModel(): ArmourApiModel {
        return ArmourApiModel(
            id,
            name,
            owned,
            icon,
            sources[0].type,
            sources[0].text
        )
    }
}

// Para convertir el modelo bonito en una entidad para la BD
fun List<ArmourApiModel>.asEntityModel(): List<ArmourEntity> {
    return this.map{
        ArmourEntity(
            it.id,
            it.name,
            it.owned,
            it.icon,
            it.text,
            it.type,
            false
        )
    }
}

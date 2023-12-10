package com.example.ffxivproject.data.api.armour

import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.MountEntity
import com.example.ffxivproject.data.api.mount.MountApiModel
import com.google.gson.Gson


data class ArmourApiModel(
    val id: Int,
    val name: String,
    val owned: String,
    val icon: String,
    val sources: List<Recursos>
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

data class ArmourDetailResponse(
    val id: Int,
    val name: String,
    val owned: String,
    val icon: String,
    val sources: List<Recursos>

) {
    fun asApiModel(): ArmourApiModel {
        return ArmourApiModel(
            id,
            name,
            owned,
            icon,
            sources
        )
    }
}

fun List<ArmourApiModel>.asEntityModel(): List<ArmourEntity> {
    val gson = Gson()
    return this.map{
        ArmourEntity(
            it.id,
            it.name,
            it.owned,
            it.icon,
            sources = gson.toJson(it.sources)
        )
    }
}

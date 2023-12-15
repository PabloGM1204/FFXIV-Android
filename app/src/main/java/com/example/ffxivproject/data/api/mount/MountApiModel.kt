package com.example.ffxivproject.data.api.mount

import com.example.ffxivproject.data.api.db.MountEntity

data class MountApiModel(
    val id: Int,
    val name: String,
    val description: String,
    val image: String
)

data class MountListApiModel(
    val list: List<MountApiModel>
)

data class MountListResponse(
    val results: List<MountListItem>
)

data class MountListItem(
    val id: Int
)

data class MountDetailResponse(
    val id: Int,
    val name: String,
    val description: String,
    val image: String
) {
    fun asApiModel(): MountApiModel {
        return MountApiModel(
            id,
            name,
            description,
            image
        )
    }
}

fun List<MountApiModel>.asEntityModel(): List<MountEntity> {
    return this.map{
        MountEntity(
            it.id,
            it.name,
            it.description,
            it.image,
            false
        )
    }
}
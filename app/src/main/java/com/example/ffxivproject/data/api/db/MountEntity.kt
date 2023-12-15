package com.example.ffxivproject.data.api.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ffxivproject.data.api.repository.Mount

@Entity(tableName = "mount")
data class MountEntity(
    @PrimaryKey
    val id:Int,
    val name:String,
    val description:String,
    val image: String,
    var obteined: Boolean
)

fun List<MountEntity>.asMount():List<Mount> {
    return this.map {
        Mount(it.name.replaceFirstChar { c -> c.uppercase() },
            it.id,
            it.description,
            it.image,
            it.obteined)
    }

}
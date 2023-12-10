package com.example.ffxivproject.data.api.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ffxivproject.data.api.armour.Recursos
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.data.api.repository.Mount

@Entity(tableName = "armour")
data class ArmourEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val owned: String,
    val icon: String,
    val sources: String
)

fun List<ArmourEntity>.asArmour():List<Armour> {
    return this.map {
        Armour(it.name.replaceFirstChar { c -> c.uppercase() },
            it.id,
            it.owned,
            it.icon,
            it.sources
        )
    }

}

package com.example.ffxivproject.data.api.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.ffxivproject.data.api.armour.Recursos
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.data.api.repository.Mount

@Entity(tableName = "armour")
data class ArmourEntity(
    @PrimaryKey
    val armourId: Int,
    val name: String,
    val owned: String,
    val icon: String,
    val type: String,
    val text: String,
    var selected: Boolean
)

fun List<ArmourEntity>.asArmour():List<Armour> {
    return this.map {
        Armour(it.name.replaceFirstChar { c -> c.uppercase() },
            it.armourId,
            it.owned,
            it.icon,
            it.type,
            it.text,
            it.selected
        )
    }

}

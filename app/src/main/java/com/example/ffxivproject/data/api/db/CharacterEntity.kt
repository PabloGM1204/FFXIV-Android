package com.example.ffxivproject.data.api.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.data.api.repository.CharacterInv


@Entity(tableName = "character")
class CharacterEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val selection: Boolean
)
@Entity(
    tableName = "character_armour",
    foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ArmourEntity::class,
            parentColumns = ["armourId"],
            childColumns = ["armourId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CharacterArmour(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val characterId: Int,
    val armourId: Int
)

fun List<CharacterEntity>.asCharacter():List<CharacterInv> {
    return this.map {
        CharacterInv(
            it.id,
            it.name,
            it.selection
        )
    }

}
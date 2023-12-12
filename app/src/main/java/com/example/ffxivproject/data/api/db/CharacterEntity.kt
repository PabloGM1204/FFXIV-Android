package com.example.ffxivproject.data.api.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.data.api.repository.Character


@Entity(tableName = "character")
class CharacterEntity (
    @PrimaryKey
    val id: Int,
    val name: String
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
    @PrimaryKey
    val id: Int,
    val characterId: Int,
    val armourId: Int
)

fun List<CharacterEntity>.asCharacter():List<Character> {
    return this.map {
        Character(
            it.id,
            it.name
        )
    }

}
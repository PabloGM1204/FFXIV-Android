package com.example.ffxivproject.data.api.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.data.api.repository.Character


@Entity(tableName = "character")
class CharacterEntity (
    @PrimaryKey
    val characterId: Int,
    val characterName: String
)
fun List<CharacterEntity>.asCharacter():List<Character> {
    return this.map {
        Character(it.characterName.replaceFirstChar { c -> c.uppercase() },
            it.characterId
        )
    }

}
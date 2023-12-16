package com.example.ffxivproject.data.api.character

import com.example.ffxivproject.data.api.armour.ArmourApiModel
import com.example.ffxivproject.data.api.armour.asEntityModel
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.CharacterEntity

data class CharacterModel(
    val id: Int,
    val name: String,
    val selection: Boolean = false,
    val kind: String
)

fun CharacterEntity.toCharacterModel(): CharacterModel{
    return CharacterModel(
        id = this.id,
        name = this.name,
        selection = this.selection,
        kind = this.kind
    )
}

fun List<CharacterModel>.asEntityModel(): List<CharacterEntity> {
    return this.map{
        CharacterEntity(
            it.id,
            it.name,
            it.selection,
            it.kind
        )
    }
}

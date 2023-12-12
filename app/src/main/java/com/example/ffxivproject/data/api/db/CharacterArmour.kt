package com.example.ffxivproject.data.api.db

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    tableName = "character_armour",
    primaryKeys = ["characterId", "armourId"],
    foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["characterId"],
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
    val characterId: Int,
    val armourId: Int
)

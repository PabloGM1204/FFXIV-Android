package com.example.ffxivproject.data.api.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

// Clase que sirve para representar la relación muchos a muchos entre character y armour
data class CharacterWithArmour(
    @Embedded // Ayuda a acceder a las propiedades de la entidad del character
    val character: CharacterEntity,
    @Relation( // Indica que hay una relacion
        parentColumn = "characterId", // Se usa la clave primaria de las siguientes columnas
        entityColumn = "armourId",
        associateBy = Junction(CharacterArmour::class) // Indica como es la tabla al hacer la relación
    )
    val armour: List<ArmourEntity>
)

package com.example.ffxivproject.data.api.character

import com.example.ffxivproject.data.api.db.ArmourEntity

data class CharacterModel(
    val id: Int,
    val name: String,
    val armours: MutableList<ArmourEntity> = mutableListOf()
) {
    fun addArmour(armour: ArmourEntity) {
        armours.add(armour)
    }
}

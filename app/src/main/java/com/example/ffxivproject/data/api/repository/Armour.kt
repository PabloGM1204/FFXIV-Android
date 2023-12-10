package com.example.ffxivproject.data.api.repository

import com.example.ffxivproject.data.api.armour.Recursos

data class Armour(
    val name: String,
    val id: Int,
    val owned: String,
    val icon: String,
    val sources: String
)

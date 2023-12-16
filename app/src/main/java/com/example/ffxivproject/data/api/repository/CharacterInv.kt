package com.example.ffxivproject.data.api.repository

data class CharacterInv(
    var id: Int,
    var name: String,
    var selection: Boolean,
    val kind: String
)

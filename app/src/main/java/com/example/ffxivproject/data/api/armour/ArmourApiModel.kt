package com.example.ffxivproject.data.api.armour

data class ArmourApiModel(
    val id: Int,
    val name: String,
    val owned: String,
    val description: String
)

data class ArmourListApiModel(
    val list: List<ArmourApiModel>
)

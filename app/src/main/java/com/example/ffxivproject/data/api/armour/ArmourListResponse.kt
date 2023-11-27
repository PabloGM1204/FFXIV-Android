package com.example.ffxivproject.data.api.armour

data class ArmourListResponse(
    val results: List<ArmourListItem>
)

data class ArmourListItem(
    val id: Int
)

data class ArmourDetailResponse(
    val id: Int,
    val name: String,
    val owned: String,
    val description: String
)

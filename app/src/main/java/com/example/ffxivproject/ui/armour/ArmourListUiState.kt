package com.example.ffxivproject.ui.armour

import com.example.ffxivproject.data.api.repository.Armour


data class ArmourListUiState(
    val armour: List<Armour>,
    val errorMessage: String?=null
)
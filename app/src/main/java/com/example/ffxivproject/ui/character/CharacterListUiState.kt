package com.example.ffxivproject.ui.character

import com.example.ffxivproject.data.api.repository.Character

data class CharacterListUiState(
    val character: List<Character>,
    val errorMessage: String?=null
)
package com.example.ffxivproject.ui.character

import com.example.ffxivproject.data.api.repository.CharacterInv

data class CharacterListUiState(
    val character: List<CharacterInv>,
    val errorMessage: String?=null
)
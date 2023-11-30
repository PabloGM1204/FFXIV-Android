package com.example.ffxivproject.ui.mount

import com.example.ffxivproject.data.api.repository.Mount

data class MountListUiState(
    val mount: List<Mount>,
    val errorMessage: String?=null
)

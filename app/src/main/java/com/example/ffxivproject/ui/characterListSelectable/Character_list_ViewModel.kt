package com.example.ffxivproject.ui.characterListSelectable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ffxivproject.data.api.repository.CharacterInv
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import com.example.ffxivproject.ui.armour.ArmourListUiState
import com.example.ffxivproject.ui.character.CharacterListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Character_list_ViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {
    private val _uiState = MutableStateFlow(CharacterListUiState(listOf()))
    val uiState: StateFlow<CharacterListUiState>
        get()=_uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.character.collect{
                _uiState.value = CharacterListUiState(it)
            }
        }
    }

    fun putCharacterWithArmour(armourId: String, lista: List<CharacterInv>){
        viewModelScope.launch {
            repository.insertCharacterArmour(armourId, lista)
        }
    }
}
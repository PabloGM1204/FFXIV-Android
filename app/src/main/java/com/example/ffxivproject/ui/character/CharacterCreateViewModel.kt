package com.example.ffxivproject.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ffxivproject.data.api.character.CharacterModel
import com.example.ffxivproject.data.api.db.CharacterEntity
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterCreateViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {
    fun createCharacter(characterName: String){
        viewModelScope.launch {
            val newCharacter = CharacterModel(
                0,
                characterName
            )
            repository.createNewCharacter(newCharacter)
        }
    }
}
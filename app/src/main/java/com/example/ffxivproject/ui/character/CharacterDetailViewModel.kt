package com.example.ffxivproject.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.CharacterEntity
import com.example.ffxivproject.data.api.repository.CharacterInv
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {
    private val _characterDetail = MutableLiveData<CharacterEntity>()
    val characterDetail: LiveData<CharacterEntity> = _characterDetail

    fun loadCharacterDetail(characterId: String){
        viewModelScope.launch {
            val detalles = repository.getCharacterId(characterId)
            _characterDetail.value = detalles
        }
    }

    suspend fun deleteCharacter(character: CharacterEntity){
        viewModelScope.launch {
            repository.deleteCharacter(character)
        }
    }
}
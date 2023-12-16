package com.example.ffxivproject.ui.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.CharacterEntity
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.data.api.repository.CharacterInv
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {
    private val _characterDetail = MutableLiveData<CharacterEntity>()
    val characterDetail: LiveData<CharacterEntity> = _characterDetail

    private val _armours = MutableLiveData<List<Armour>>()
    val armours: LiveData<List<Armour>> = _armours
    val nombresArmours: LiveData<List<String>> = armours.map { armoursList ->
        armoursList.map { armour ->
            armour.name // Reemplaza "nombre" con el nombre del campo que deseas obtener
        }
    }

    fun loadCharacterDetail(characterId: String){
        viewModelScope.launch {
            val detalles = repository.getCharacterId(characterId)
            _characterDetail.value = detalles
            Log.d("Characters", repository.getCharacterWithArmours(characterId).toString())
            val armaduras = repository.getCharacterWithArmours(characterId)
            _armours.value = armaduras
        }
    }

    suspend fun deleteCharacter(character: CharacterEntity){
        viewModelScope.launch {
            repository.deleteCharacter(character)
        }
    }
}
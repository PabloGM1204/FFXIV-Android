package com.example.ffxivproject.ui.armour

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.MountEntity
import com.example.ffxivproject.data.api.repository.CharacterInv
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArmourDetailViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {
    private val _armourDetail = MutableLiveData<ArmourEntity>()
    val armourDetail: LiveData<ArmourEntity> = _armourDetail

    private val _characterList = MutableLiveData<List<CharacterInv>>()
    val characterList: LiveData<List<CharacterInv>> = _characterList

    fun loadArmourDetail(armourId: String){
        viewModelScope.launch {
            val detalles = repository.getArmourId(armourId)
            _armourDetail.value = detalles
        }
    }

    fun loadListCharacter(){
        viewModelScope.launch {
            repository.character.collect{
                _characterList.value = it
            }
        }
    }

    suspend fun updateArmour(armourId: String) {
        repository.updateArmourSelected(armourId)
    }
}
package com.example.ffxivproject.ui.mount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ffxivproject.data.api.db.MountEntity
import com.example.ffxivproject.data.api.mount.MountApiRepository
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import com.example.ffxivproject.data.api.repository.Mount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MountDetailViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {
    private val _mountDetail = MutableLiveData<MountEntity>()
    val mountDetail: LiveData<MountEntity> = _mountDetail

    fun loadMountDetail(mountId: String){
        viewModelScope.launch {
            val detalles = repository.getMountId(mountId)
            _mountDetail.value = detalles
        }
    }
}
package com.example.ffxivproject.ui.mount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MountListViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {
    private val _uiState = MutableStateFlow(MountListUiState(listOf()))
    val uiState: StateFlow<MountListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.refreshList()
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message!!)
            }
        }

        viewModelScope.launch {
            repository.mount.collect {
                _uiState.value = MountListUiState(it)
            }
        }
    }
}
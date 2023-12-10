package com.example.ffxivproject.ui.armour

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
class ArmourListViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {
    private val _uiState = MutableStateFlow (ArmourListUiState(listOf()))
    val uiState: StateFlow<ArmourListUiState>
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
            repository.armour.collect {
                _uiState.value = ArmourListUiState(it)
            }
        }
    }


}

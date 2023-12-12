package com.example.ffxivproject.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import com.example.ffxivproject.ui.armour.ArmourListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: FFXIVRepository): ViewModel() {

}
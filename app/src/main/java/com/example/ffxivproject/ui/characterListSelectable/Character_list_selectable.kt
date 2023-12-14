package com.example.ffxivproject.ui.characterListSelectable

import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ffxivproject.R
import com.example.ffxivproject.data.api.character.CharacterModel
import com.example.ffxivproject.data.api.repository.CharacterInv
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import com.example.ffxivproject.databinding.FragmentCharacterListBinding
import com.example.ffxivproject.databinding.FragmentCharacterListSelectableBinding
import com.example.ffxivproject.ui.armour.ArmourDetailFragmentArgs
import com.example.ffxivproject.ui.character.CharacterListAdapter
import com.example.ffxivproject.ui.character.CharacterListFragmentDirections
import com.example.ffxivproject.ui.character.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class Character_list_selectable : Fragment() {
    private lateinit var binding: FragmentCharacterListSelectableBinding
    private val args: Character_list_selectableArgs by navArgs()
    private val viewModel: Character_list_ViewModel by viewModels()
    @Inject
    lateinit var repository: FFXIVRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListSelectableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = Character_list_Adapter(requireContext())
        val rv = binding.characterList
        rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect{
                    Log.d("Listas", it.character.toString())
                    adapter.submitList(it.character)
                    binding.topAppBar.setNavigationOnClickListener(){
                        findNavController().popBackStack()
                    }
                }
            }
        }

        binding.confirmButton.setOnClickListener {
            Log.d("Pruebas", adapter.listasSeleccionadas.toString())
            Log.d("CharacterTop", args.armourID.toString())
            findNavController().popBackStack()
        }
    }
}
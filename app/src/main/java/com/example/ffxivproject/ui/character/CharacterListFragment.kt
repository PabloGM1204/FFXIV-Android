package com.example.ffxivproject.ui.character

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.ffxivproject.data.api.character.CharacterModel
import com.example.ffxivproject.data.api.character.CharacterRepository
import com.example.ffxivproject.data.api.repository.FFXIVRepository
import com.example.ffxivproject.databinding.FragmentCharacterListBinding
import com.example.ffxivproject.databinding.FragmentCharacterListSelectableBinding
import com.example.ffxivproject.ui.characterListSelectable.Character_list_ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()
    @Inject
    lateinit var repository: FFXIVRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharacterListAdapter(requireContext()) {view, character ->
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(character.id.toString())
            view.findNavController().navigate(action)
        }
        val rv = binding.armourList
        rv.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect{
                    adapter.submitList(it.character)
                }
            }
        }
        binding.createCharacter.setOnClickListener{
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCreateCharacterFragment()
            view.findNavController().navigate(action)
        }
    }
}
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
        val adapter = CharacterListAdapter(requireContext()) {view, characterInv ->
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment()
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
            showCreateListDialog()
        }
    }

    private fun showCreateListDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Crear Lista")

        // Creo un EditText para que el usuario ingrese el nombre de la lista
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Botón positivo para confirmar y crear la lista
        builder.setPositiveButton("Crear") { _, _ ->
            val listName = input.text.toString()
            val newCharacter = CharacterModel(
                0,
                listName
            )
            viewModel.viewModelScope.launch {
                repository.createNewCharacter(newCharacter)
            }
            // Muestro un Toast con el nombre de la lista que he creado
            Toast.makeText(requireContext(), "Lista creada: $listName", Toast.LENGTH_SHORT).show()
        }

        // Botón negativo para cancelar el diálogo
        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}
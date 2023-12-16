package com.example.ffxivproject.ui.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ffxivproject.R
import com.example.ffxivproject.data.api.db.CharacterEntity
import com.example.ffxivproject.databinding.FragmentCharacterDetailBinding
import com.example.ffxivproject.databinding.FragmentCreateCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CreateCharacterFragment : Fragment() {
    private lateinit var binding: FragmentCreateCharacterBinding
    private val viewModel: CharacterCreateViewModel by viewModels()
    val observer = Observer<CharacterEntity>{
        binding.topAppBar.setNavigationOnClickListener(){
            findNavController().popBackStack(R.id.characterListFragment, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateCharacterBinding.inflate(inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }
        // Lista de las opciones
        val opciones: Array<String> = arrayOf (
            getString(R.string.healer),
            getString(R.string.damage),
            getString(R.string.tank),
            getString(R.string.wizard)
        )
        var kind: String = ""
        // Creo un ArrayAdapter usando el arreglo de opciones
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opciones)
        // Configuro el estilo del desplegable del ArrayAdapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerOptions.adapter = adapter
        binding.spinnerOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Manejar la opción seleccionada
                val opcionSeleccionada = opciones[position]
                kind = opcionSeleccionada
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                Toast.makeText(requireContext(), "Debe seleccionar una opción", Toast.LENGTH_SHORT).show()
            }
        }

        binding.changeButton.setOnClickListener{
            viewModel.createCharacter(binding.nameText.text.toString(), kind)
            findNavController().popBackStack()
        }
    }
}
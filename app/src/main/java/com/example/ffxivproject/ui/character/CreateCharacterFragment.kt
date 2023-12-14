package com.example.ffxivproject.ui.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.changeButton.setOnClickListener{
            Log.d("Prueba", "Boton click")
            viewModel.createCharacter(binding.nameText.text.toString())
            findNavController().popBackStack()
        }
    }
}
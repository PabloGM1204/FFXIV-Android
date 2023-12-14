package com.example.ffxivproject.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ffxivproject.R
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.CharacterEntity
import com.example.ffxivproject.databinding.FragmentArmourDetailBinding
import com.example.ffxivproject.databinding.FragmentCharacterDetailBinding
import com.example.ffxivproject.ui.armour.ArmourDetailFragmentArgs
import com.example.ffxivproject.ui.armour.ArmourDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: CharacterDetailViewModel by viewModels()
    //private val listCharacters = viewModel.characterList

    val observer = Observer<CharacterEntity>{character ->
        binding.topAppBar.setNavigationOnClickListener(){
            findNavController().popBackStack(R.id.characterListFragment, false)
        }
        binding.characterId.text = character.id.toString()
        binding.characterName.text = character.name
        binding.btnDelete.setOnClickListener{
            viewModel.viewModelScope.launch {
                viewModel.deleteCharacter(character)
                findNavController().popBackStack(R.id.characterListFragment, false)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater,
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

        viewModel.loadCharacterDetail(args.characterID)

        viewModel.characterDetail.observe(viewLifecycleOwner, observer)
    }
}
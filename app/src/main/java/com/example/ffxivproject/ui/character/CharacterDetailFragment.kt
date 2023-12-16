package com.example.ffxivproject.ui.character

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import androidx.lifecycle.Observer
import com.example.ffxivproject.R
import com.example.ffxivproject.data.api.db.CharacterEntity
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: CharacterDetailViewModel by viewModels()
    private var nombresArmours: List<Armour> = mutableListOf()

    val observer = Observer<CharacterEntity>{character ->
        binding.topAppBar.setNavigationOnClickListener(){
            findNavController().popBackStack(R.id.characterListFragment, false)
        }
        binding.characterName.text = character.name
        when(character.kind){
            "Wizard" -> binding.imageTipo.load(R.drawable.wizard)
            "Damage" -> binding.imageTipo.load(R.drawable.sword)
            "Healer" -> binding.imageTipo.load(R.drawable.regeneration)
            "Tank" -> binding.imageTipo.load(R.drawable.shield)
        }
        binding.btnDelete.setOnClickListener{
            viewModel.viewModelScope.launch {
                viewModel.deleteCharacter(character)
                findNavController().popBackStack(R.id.characterListFragment, false)
            }
        }
        Log.d("Kind", character.kind)
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
        val adapter = ArmourListAdapter(requireContext())
        val rv = binding.armourList
        rv.adapter = adapter

        viewModel.armours.observe(viewLifecycleOwner) { armours ->
            // Este bloque se ejecutará cada vez que armours cambie
            Log.d("Listas", armours.toString())
            // Calcular el promedio solo si hay armaduras en la lista
            if (armours.isNotEmpty()) {
                nombresArmours = armours
                // Calcular la suma de los porcentajes eliminando el símbolo de porcentaje
                val sumPorcentaje = armours.sumByDouble { it.owned.removeSuffix("%").toDouble() }
                sumPorcentaje / armours.size
                binding.characterPorcentaje.text = String.format("%.2f", sumPorcentaje)+"%"
            } else {
                binding.characterPorcentaje.text = "0.0"
            }
            adapter.submitList(armours)
        }

        viewModel.characterDetail.observe(viewLifecycleOwner) { character ->
            binding.btnShare.setOnClickListener{
                if (nombresArmours != null) {
                    onShareItem(character, nombresArmours)
                } else {
                    Toast.makeText(context, "Debe tener armaduras seleccionadas", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loadCharacterDetail(args.characterID)

        viewModel.characterDetail.observe(viewLifecycleOwner, observer)
    }

    fun onShareItem(character: CharacterEntity, listArmour: List<Armour>){
        val armorNames = listArmour.joinToString(separator = ", ") { it.name }
        val shareText = "${character.name} ${character.kind}: $armorNames"
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }
}
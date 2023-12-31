package com.example.ffxivproject.ui.armour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.ffxivproject.R
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.databinding.FragmentArmourDetailBinding
import com.example.ffxivproject.ui.character.CharacterListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.reflect.Array

@AndroidEntryPoint
class ArmourDetailFragment : Fragment() {
    private lateinit var binding: FragmentArmourDetailBinding
    private val args: ArmourDetailFragmentArgs by navArgs()
    private val viewModel: ArmourDetailViewModel by viewModels()

    val observer = Observer<ArmourEntity>{
        binding.topAppBar.setNavigationOnClickListener(){
            findNavController().popBackStack(R.id.armourListFragment, false)
        }
        binding.armourId.text = it.armourId.toString()
        binding.armourName.text = it.name
        binding.armourText.text = it.text
        binding.armourType.text = it.type
        binding.isSelected.isChecked = it.selected
        // Para cuando cambie el valor de añadir al inventario
        binding.isSelected.setOnCheckedChangeListener{ _, _ ->
            // Lanzamos en la corrutina del viewModel el actualizar el dato
            viewModel.viewModelScope.launch {
                viewModel.updateArmour(it.armourId.toString())
            }
        }
        binding.armourImg.load(it.icon)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArmourDetailBinding.inflate(inflater,
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
        viewModel.loadArmourDetail(args.armourID)
        viewModel.armourDetail.observe(viewLifecycleOwner, observer)
        binding.listaButton.setOnClickListener{
            val action = ArmourDetailFragmentDirections.actionArmourDetailFragmentToCharacterListSelectableFragment(binding.armourId.text.toString())
            view.findNavController().navigate(action)
        }
    }
}
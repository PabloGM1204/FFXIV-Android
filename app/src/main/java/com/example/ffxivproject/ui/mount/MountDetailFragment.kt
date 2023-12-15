package com.example.ffxivproject.ui.mount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.ffxivproject.R
import com.example.ffxivproject.data.api.db.MountEntity
import com.example.ffxivproject.data.api.repository.Mount
import com.example.ffxivproject.databinding.FragmentMountDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MountDetailFragment : Fragment() {
    private lateinit var binding: FragmentMountDetailBinding
    private val args: MountDetailFragmentArgs by navArgs()
    private val viewModel: MountDetailViewModel by viewModels()

    val observer = Observer<MountEntity>{
        binding.topAppBar.setNavigationOnClickListener(){
            findNavController().popBackStack(R.id.mountListFragment, false)
        }
        binding.mountId.text = it.id.toString()
        binding.mountName.text = it.name
        binding.mountDescription.text = it.description
        binding.mountImg.load(it.image)
        binding.isObtained.isChecked = it.obteined
        // Para cuando cambie el valor del obtenido
        binding.isObtained.setOnCheckedChangeListener{ _, _ ->
            // Lanzamos en la corrutina del viewModel el actualizar el dato
            viewModel.viewModelScope.launch {
                viewModel.updateMount(it.id.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMountDetailBinding.inflate(inflater,
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

        viewModel.loadMountDetail(args.mountID)

        viewModel.mountDetail.observe(viewLifecycleOwner, observer)
    }
}
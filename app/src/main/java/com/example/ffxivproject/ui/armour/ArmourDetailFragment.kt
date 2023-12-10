package com.example.ffxivproject.ui.armour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.ffxivproject.R
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.databinding.FragmentArmourDetailBinding
import com.example.ffxivproject.databinding.FragmentMountDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArmourDetailFragment : Fragment() {
    private lateinit var binding: FragmentArmourDetailBinding
    private val args: ArmourDetailFragmentArgs by navArgs()
    private val viewModel: ArmourDetailViewModel by viewModels()

    val observer = Observer<ArmourEntity>{
        binding.topAppBar.setNavigationOnClickListener(){
            findNavController().popBackStack(R.id.armourListFragment, false)
        }
        binding.armourId.text = it.id.toString()
        binding.armourName.text = it.name
        binding.armourDescription.text = it.sources
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
    }
}
package com.example.ffxivproject.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.ffxivproject.databinding.FragmentArmourListBinding
import com.example.ffxivproject.databinding.FragmentCharacterListBinding
import com.example.ffxivproject.ui.armour.ArmourListAdapter
import com.example.ffxivproject.ui.armour.ArmourListFragmentDirections
import com.example.ffxivproject.ui.armour.ArmourListViewModel
import kotlinx.coroutines.launch


class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()
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
    }
}
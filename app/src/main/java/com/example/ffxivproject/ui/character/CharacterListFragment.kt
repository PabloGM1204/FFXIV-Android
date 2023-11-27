package com.example.ffxivproject.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ffxivproject.databinding.FragmentArmourListBinding
import com.example.ffxivproject.databinding.FragmentCharacterListBinding


class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding
    //private val viewModel:
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
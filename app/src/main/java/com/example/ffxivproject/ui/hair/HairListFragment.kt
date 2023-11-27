package com.example.ffxivproject.ui.hair

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ffxivproject.R
import com.example.ffxivproject.databinding.FragmentArmourListBinding
import com.example.ffxivproject.databinding.FragmentHairListBinding


class HairListFragment : Fragment() {
    private lateinit var binding: FragmentHairListBinding
    //private val viewModel:
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHairListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
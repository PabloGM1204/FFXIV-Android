package com.example.ffxivproject.ui.armour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ffxivproject.R
import com.example.ffxivproject.databinding.FragmentArmourListBinding


class ArmourListFragment : Fragment() {
    private lateinit var binding: FragmentArmourListBinding
    //private val viewModel:
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArmourListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
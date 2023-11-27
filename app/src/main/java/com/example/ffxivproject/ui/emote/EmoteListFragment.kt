package com.example.ffxivproject.ui.emote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ffxivproject.R
import com.example.ffxivproject.databinding.FragmentArmourListBinding
import com.example.ffxivproject.databinding.FragmentEmoteListBinding

class EmoteListFragment : Fragment() {
    private lateinit var binding: FragmentEmoteListBinding
    //private val viewModel:
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmoteListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
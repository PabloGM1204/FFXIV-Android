package com.example.ffxivproject.ui.armour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.ffxivproject.R
import com.example.ffxivproject.databinding.FragmentArmourListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArmourListFragment : Fragment() {
    private lateinit var binding: FragmentArmourListBinding
    private val viewModel: ArmourListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArmourListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArmourListAdapter(requireContext()) {view, armour ->
            val action = ArmourListFragmentDirections.actionArmourListFragmentToArmourDetailFragment(armour.id.toString())
            view.findNavController().navigate(action)
        }
        val rv = binding.armourList
        rv.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect{
                    adapter.submitList(it.armour)
                }
            }
        }
    }
}
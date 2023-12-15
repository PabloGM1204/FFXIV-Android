package com.example.ffxivproject.ui.character

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.data.api.repository.CharacterInv
import com.example.ffxivproject.databinding.ArmourCharacterListItemBinding

class ArmourListAdapter (private val context: Context): ListAdapter<Armour, ArmourListAdapter.CharacterViewHolder>(ArmourDiffCallBack) {

    inner class CharacterViewHolder(private val binding: ArmourCharacterListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(armour: Armour) {
            binding.armourName.text = armour.name
            binding.armourIcon.load(armour.icon)
            Log.d("Imagen", armour.icon)
        }
    }

    private object ArmourDiffCallBack: DiffUtil.ItemCallback<Armour>() {
        override fun areItemsTheSame(oldItem: Armour, newItem: Armour) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Armour, newItem: Armour) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ArmourCharacterListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(getItem(position))
}
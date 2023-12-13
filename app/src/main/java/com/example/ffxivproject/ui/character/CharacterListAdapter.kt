package com.example.ffxivproject.ui.character

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.data.api.repository.CharacterInv
import com.example.ffxivproject.databinding.ArmourListItemBinding
import com.example.ffxivproject.databinding.CharacterListItemBinding
import com.example.ffxivproject.ui.armour.ArmourListAdapter

class CharacterListAdapter (private val context: Context, private val onClick: ((View, CharacterInv) -> Unit)): ListAdapter<CharacterInv, CharacterListAdapter.CharacterViewHolder>(CharacterDiffCallBack) {

    inner class CharacterViewHolder(private val binding: CharacterListItemBinding, private val onClick: ((View, CharacterInv) -> Unit)): RecyclerView.ViewHolder(binding.root){
        fun bind(character: CharacterInv) {
            binding.characterName.text = character.name
            binding.characterId.text = character.id.toString()
            binding.tarjetaBoton.setOnClickListener{
                onClick(it, character)
            }
        }
    }

    private object CharacterDiffCallBack: DiffUtil.ItemCallback<CharacterInv>() {
        override fun areItemsTheSame(oldItem: CharacterInv, newItem: CharacterInv) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CharacterInv, newItem: CharacterInv) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return CharacterViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(getItem(position))
}
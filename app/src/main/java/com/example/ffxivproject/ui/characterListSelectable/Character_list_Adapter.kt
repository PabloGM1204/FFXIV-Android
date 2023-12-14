package com.example.ffxivproject.ui.characterListSelectable

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ffxivproject.data.api.repository.CharacterInv
import com.example.ffxivproject.databinding.CharacterListItemBinding
import com.example.ffxivproject.databinding.CharacterListSelectableItemBinding
import com.example.ffxivproject.ui.character.CharacterListAdapter

class Character_list_Adapter (private val context: Context): ListAdapter<CharacterInv, Character_list_Adapter.CharacterListViewHolder>(CharacterListDiffCallBack) {

    val listasSeleccionadas = mutableListOf<CharacterInv>()

    inner class CharacterListViewHolder(private val binding: CharacterListSelectableItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(character: CharacterInv) {
            binding.characterName.text = character.name
            binding.checkBox.isChecked = character.selection
            binding.checkBox.setOnClickListener {
                character.selection = binding.checkBox.isChecked
            }
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                character.selection = isChecked

                if (isChecked) {
                    listasSeleccionadas.add(character)
                } else {
                    listasSeleccionadas.remove(character)
                }
            }

            Log.d("Seleccionadas", listasSeleccionadas.toString())

        }
    }

    private object CharacterListDiffCallBack: DiffUtil.ItemCallback<CharacterInv>() {
        override fun areItemsTheSame(oldItem: CharacterInv, newItem: CharacterInv) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CharacterInv, newItem: CharacterInv) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Character_list_Adapter.CharacterListViewHolder {
        val binding = CharacterListSelectableItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) = holder.bind(getItem(position))



}
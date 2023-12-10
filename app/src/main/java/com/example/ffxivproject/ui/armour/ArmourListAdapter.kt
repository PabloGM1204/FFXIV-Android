package com.example.ffxivproject.ui.armour

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ffxivproject.data.api.armour.ArmourListItem
import com.example.ffxivproject.data.api.repository.Armour
import com.example.ffxivproject.databinding.ArmourListItemBinding

class ArmourListAdapter(private val context: Context, private val onClick: ((View, Armour) -> Unit)): ListAdapter<Armour, ArmourListAdapter.ArmourViewHolder> (ArmourDiffCallBack) {
    inner class ArmourViewHolder(private val binding: ArmourListItemBinding, private val onClick: ((View, Armour) -> Unit)): RecyclerView.ViewHolder(binding.root){
        fun bind(armour: Armour) {
            binding.armourName.text = armour.name
            binding.armourIcon.load(armour.icon)
            binding.tarjetaBoton.setOnClickListener{
                onClick(it, armour)
            }
        }
    }

    private object ArmourDiffCallBack: DiffUtil.ItemCallback<Armour>() {
        override fun areItemsTheSame(oldItem: Armour, newItem: Armour) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Armour, newItem: Armour) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmourViewHolder {
        val binding = ArmourListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return ArmourViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ArmourViewHolder, position: Int) = holder.bind(getItem(position))
}
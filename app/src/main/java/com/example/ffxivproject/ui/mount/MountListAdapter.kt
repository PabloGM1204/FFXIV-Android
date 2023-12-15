package com.example.ffxivproject.ui.mount

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ffxivproject.data.api.repository.Mount
import com.example.ffxivproject.databinding.MountListItemBinding

class MountListAdapter(private val context: Context, private val onClick: ((View, Mount) -> Unit)): ListAdapter<Mount, MountListAdapter.MountViewHolder> (MountDiffCallback) {
    inner class MountViewHolder(private val binding: MountListItemBinding, private val onClick: ((View, Mount) -> Unit)): RecyclerView.ViewHolder(binding.root){
        fun bind(mount: Mount) {
            binding.mountName.text = mount.name
            binding.mountSprite.load(mount.image)
            binding.tarjetaBoton.setOnClickListener{
                onClick(it, mount)
            }
            if(mount.obteined){
                binding.mountSelected.visibility = View.VISIBLE
            } else {
                binding.mountSelected.visibility = View.GONE
            }
        }
    }


    private object MountDiffCallback: DiffUtil.ItemCallback<Mount>() {
        override fun areItemsTheSame(oldItem: Mount, newItem: Mount) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Mount, newItem: Mount) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountViewHolder {
        val binding = MountListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return MountViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MountViewHolder, position: Int) = holder.bind(getItem(position))

}
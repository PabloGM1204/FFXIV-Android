package com.example.ffxivproject.ui.mount

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ffxivproject.data.api.repository.Mount
import com.example.ffxivproject.databinding.MountListItemBinding

class MountListAdapter(private val context: Context): ListAdapter<Mount, MountListAdapter.MountViewHolder> (MountDiffCallback) {
    inner class MountViewHolder(private val binding: MountListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(mount: Mount) {
            binding.mountName.text = mount.name
            binding.mountSprite.load(mount.image)
        }
    }


    private object MountDiffCallback: DiffUtil.ItemCallback<Mount>() {
        override fun areItemsTheSame(oldItem: Mount, newItem: Mount) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Mount, newItem: Mount) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountViewHolder = MountViewHolder(MountListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: MountViewHolder, position: Int) = holder.bind(getItem(position))

}
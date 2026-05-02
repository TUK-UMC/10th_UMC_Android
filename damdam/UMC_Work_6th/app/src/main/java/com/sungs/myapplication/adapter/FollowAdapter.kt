package com.sungs.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungs.myapplication.data.UserData
import com.sungs.myapplication.databinding.ItemFollowBinding

class FollowAdapter
    : ListAdapter<UserData, FollowAdapter.FollowViewHolder>(FollowDiffCallback()) {

    inner class FollowViewHolder(
        private val binding: ItemFollowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserData) {
            Glide.with(binding.ivFollowAvatar)
                .load(user.avatar)
                .into(binding.ivFollowAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding = ItemFollowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FollowDiffCallback : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean =
            oldItem == newItem
    }
}
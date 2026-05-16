package com.example.week5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week5.databinding.ItemFollowingBinding

class FollowingAdapter : ListAdapter<UserDto, FollowingAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(val binding: ItemFollowingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.tvFollowingName.text = user.firstName
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.ivFollowingAvatar)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UserDto>() {
        override fun areItemsTheSame(oldItem: UserDto, newItem: UserDto) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: UserDto, newItem: UserDto) = oldItem == newItem
    }
}

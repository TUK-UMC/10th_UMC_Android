package com.example.week4.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week4.databinding.ItemFollowingBinding


class FollowAdapter : ListAdapter<UserData, FollowAdapter.FollowViewHolder>(FollowDiffCallback()) {

    inner class FollowViewHolder(
        private val binding: ItemFollowingBinding // 이름을 XML 파일명과 일치시킴
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserData) {
            // 이미지 로딩
            Glide.with(binding.ivFollowAvatar.context)
                .load(user.avatar)
                .into(binding.ivFollowAvatar)

            // 이름 데이터 바인딩 (XML에 관련 ID가 있다면 추가)
            // binding.tvFollowName.text = "${user.firstName} ${user.lastName}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        // LayoutInflater 사용 시 parent.context 확인
        val binding = ItemFollowingBinding.inflate(
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






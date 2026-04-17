package com.example.week5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week5.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var followingAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingAdapter = FollowingAdapter()
        binding.rvFollowing.apply {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        profileViewModel.userProfile.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { user ->
                    binding.tvProfileName.text = "${user.firstName} ${user.lastName}"
                    Glide.with(this)
                        .load(user.avatar)
                        .circleCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(binding.ivProfileAvatar)
                },
                onFailure = { e -> Log.e("ProfileFragment", "유저 로드 실패", e) }
            )
        }

        profileViewModel.followingList.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { users ->
                    followingAdapter.submitList(users)
                    binding.tvFollowingTitle.text = "팔로잉 (${users.size})"
                },
                onFailure = { e -> Log.e("ProfileFragment", "팔로잉 로드 실패", e) }
            )
        }

        binding.btnEditProfile.setOnClickListener {
            val intent = Intent(requireActivity(), ProfileEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

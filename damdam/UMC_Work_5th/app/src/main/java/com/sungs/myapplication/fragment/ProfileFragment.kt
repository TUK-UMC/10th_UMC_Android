package com.sungs.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sungs.myapplication.R
import com.sungs.myapplication.adapter.FollowAdapter
import com.sungs.myapplication.databinding.FragmentProfileBinding
import com.sungs.myapplication.network.ApiClient
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val followAdapter = FollowAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupEditButton()
        loadUserProfile()
        loadFollowingList()
    }

    private fun setupRecyclerView() {
        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = followAdapter
        }
    }

    private fun setupEditButton() {
        binding.btnEditProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileEditFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun loadUserProfile() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.reqResService.getUser(id = 1)
                val user = response.data

                binding.tvProfileNickname.text = "${user.firstName} ${user.lastName}"

                Glide.with(binding.ivProfileAvatar)
                    .load(user.avatar)
                    .into(binding.ivProfileAvatar)

                Log.d("ProfileFragment", "유저 로드 성공: $user")
            } catch (e: Exception) {
                Log.e("ProfileFragment", "유저 로드 실패", e)
            }
        }
    }

    private fun loadFollowingList() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.reqResService.getUsers(page = 1)

                followAdapter.submitList(response.data)
                binding.tvFollowingCount.text = "팔로잉 (${response.data.size})"

                Log.d("ProfileFragment", "팔로잉 로드 성공: ${response.data.size}명")
            } catch (e: Exception) {
                Log.e("ProfileFragment", "팔로잉 로드 실패", e)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
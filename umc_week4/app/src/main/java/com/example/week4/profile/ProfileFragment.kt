package com.example.week4.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.week4.databinding.FragmentProfileBinding
import com.example.week4.R
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val followAdapter = FollowAdapter() // 어댑터 선언

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 리사이클러뷰 설정
        binding.rvFollowing.adapter = followAdapter

        // 2. 서버 데이터 호출 (예시: Coroutine 사용)
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.reqResService.getUsers(page = 1)
                followAdapter.submitList(response.data) // 데이터 반영
            } catch (e: Exception) {
                Log.e("ProfileFragment", "Error fetching users: ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
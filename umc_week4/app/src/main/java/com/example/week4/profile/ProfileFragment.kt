package com.example.week4.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
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

        // userId 1번 정보 가져오기
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // 1번 유저 데이터 요청
                val response = ApiClient.reqResService.getUser(1)
                val user = response.data

                // 1. 이미지 표시 (Glide)
                Glide.with(this@ProfileFragment)
                    .load(user.avatar)
                    .circleCrop() // 원형 이미지 처리
                    .into(binding.imgProfile) // XML의 이미지뷰 ID 확인

                // 2. 닉네임 표시 (first name + last name)
                val fullName = "${user.firstName} ${user.lastName}"
                binding.tvProfileName.text = fullName // XML의 텍스트뷰 ID 확인

            } catch (e: Exception) {
                Log.e("API_ERROR", "1번 유저 데이터를 가져오는데 실패했습니다: ${e.message}")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
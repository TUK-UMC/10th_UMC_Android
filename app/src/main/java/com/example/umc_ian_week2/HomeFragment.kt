package com.example.umc_ian_week2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_ian_week2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //Fragment의 View를 생성
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

     // 실행 UI 초기화 / 이벤트 처리
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        setupClickListeners()
    }
     //UI 초기 설정
    private fun initUI() {
        // 예시: 텍스트 설정
        // binding.tvTitle.text = "홈 화면"
    }
     // 클릭 이벤트 처리
    private fun setupClickListeners() {
        // 예시:
        // binding.btnExample.setOnClickListener { }
    }

    //메모리 누수 방지를 위해 binding 제거
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
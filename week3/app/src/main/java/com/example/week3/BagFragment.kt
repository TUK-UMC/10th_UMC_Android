package com.example.week3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.week3.R
import com.example.week3.databinding.FragmentBagBinding


class BagFragment : Fragment() {

    private var _binding: FragmentBagBinding? = null
    private val binding get() = _binding!!

     //Fragment의 View를 생성
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ViewBinding 초기화
        _binding = FragmentBagBinding.inflate(inflater, container, false)
        return binding.root
    }

     // 클릭 이벤트, 데이터 바인딩 등 UI 로직 처리
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnOrder.setOnClickListener {

        }
    }

    //메모리 누수 방지를 위해 binding 제거

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
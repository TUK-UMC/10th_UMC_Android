package com.example.week3.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week3.databinding.FragmentProfileEditBinding

class ProfileEditFragment : Fragment() {

    // ViewBinding (메모리 누수 방지)
    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!


    //View 생성 (레이아웃 inflate만 담당)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    // UI 초기화 및 이벤트 처리
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        setupClickListeners()
    }

     //UI 초기 설정
    private fun initUI() {
    }

     //클릭 이벤트 처리
    private fun setupClickListeners() {

        // 저장 버튼 클릭
        binding.btnSave.setOnClickListener {
            saveProfile()
        }

        // 뒤로가기 버튼 (예시)
        binding.btnBack.setOnClickListener {
            goBack()
        }
    }

    //프로필 저장 로직
    private fun saveProfile() {

    }

    //이전 화면으로 돌아가기
    private fun goBack() {
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
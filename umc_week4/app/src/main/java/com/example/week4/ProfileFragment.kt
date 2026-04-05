package com.example.week4.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week4.R
import com.example.week4.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    // ViewBinding (메모리 누수 방지 패턴)
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

     // View 생성 (레이아웃 inflate만 담당)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    //UI 설정 및 이벤트 연결
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    //클릭 이벤트 모음
    private fun setupClickListeners() {
        binding.btnEditProfile.setOnClickListener {
            navigateToEditProfile()
        }
    }

     //프로필 수정 화면으로 이동
    private fun navigateToEditProfile() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProfileEditFragment())
            .addToBackStack(null) // 뒤로가기 가능
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
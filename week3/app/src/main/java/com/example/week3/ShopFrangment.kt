package com.example.week3.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week3.databinding.FragmentShopBinding
class ShopFragment : Fragment() {

    // ViewBinding (메모리 누수 방지 패턴)
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!


     //View 생성 (레이아웃 inflate만 담당)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }


    //UI 초기화 및 이벤트 처리
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        setupClickListeners()
    }

     // UI 초기 설정
    private fun initUI() {
        // 예시:
        // binding.tvTitle.text = "Shop"
    }

     //클릭 이벤트 처리
     private fun setupClickListeners() {
        // 예시:
        // binding.btnBuy.setOnClickListener { }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
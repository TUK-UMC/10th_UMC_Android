package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week2.databinding.FragmentBagBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BagFragment : Fragment() {
    private var _binding: FragmentBagBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBagBinding.inflate(inflater, container, false)

        binding.bagBtnOrder.setOnClickListener {
            requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav).selectedItemId = R.id.nav_buy
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
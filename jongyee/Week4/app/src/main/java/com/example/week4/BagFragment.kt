package com.example.week4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week4.databinding.FragmentBagBinding

class BagFragment : Fragment() {
    private var _binding: FragmentBagBinding? = null
    private val binding get() = _binding!!

    private var navigator: NavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) {
            navigator = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBagBinding.inflate(inflater, container, false)
            binding.bagBtnOrder.setOnClickListener {
                navigator?.onNavigateToBuyTab()
            }

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
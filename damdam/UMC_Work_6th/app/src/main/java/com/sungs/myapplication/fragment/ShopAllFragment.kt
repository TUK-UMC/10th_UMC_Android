package com.sungs.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.sungs.myapplication.adapter.ShopProductAdapter
import com.sungs.myapplication.databinding.FragmentShopAllBinding
import com.sungs.myapplication.viewmodel.ShopAllViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopAllFragment : Fragment() {

    private var _binding: FragmentShopAllBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShopAllViewModel by viewModels()

    private lateinit var adapter: ShopProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = ShopProductAdapter { product ->
            viewModel.toggleFavorite(product.name)
        }

        binding.rvShopAll.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvShopAll.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    adapter.submitList(state.products)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

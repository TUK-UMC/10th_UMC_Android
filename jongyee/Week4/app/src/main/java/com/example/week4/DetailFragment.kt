package com.example.week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.week4.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    // ViewModel 연결
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.selectedProduct.collect { product ->
                product?.let {
                    binding.tvDetailCategory.text = it.category
                    binding.tvDetailName.text = it.name
                    binding.tvDetailPrice.text = it.price
                    binding.tvDetailDescription.text = it.description
                    binding.imgDetailProduct.setImageResource(it.imageRes)

                    val heartIcon = if (it.isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_empty
                    binding.btnWishlist.setIconResource(heartIcon)
                }
            }
        }

        binding.btnWishlist.setOnClickListener {
            sharedViewModel.selectedProduct.value?.let { currentProduct ->
                sharedViewModel.toggleFavorite(currentProduct)
            }
        }

        binding.icBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
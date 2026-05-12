package com.example.week3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.week3.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    interface OnDetailActionListener {
        fun onAddToBag(product: Product)
        fun onAddToWishlist(product: Product)
        fun onBack()
    }

    private var listener: OnDetailActionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDetailActionListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name") ?: ""
        val price = arguments?.getString("price") ?: ""
        val desc = arguments?.getString("desc") ?: ""
        val img = arguments?.getInt("img") ?: R.drawable.image
        val category = arguments?.getString("category") ?: ""

        binding.apply {
            tvDetailName.text = name
            tvDetailPrice.text = price
            tvDetailDescription.text = desc
            imgDetailProduct.setImageResource(img)
            tvDetailCategory.text = category

            icBack.setOnClickListener {
                listener?.onBack() ?: parentFragmentManager.popBackStack()
            }

            btnAddToBag.setOnClickListener {
                val currentProduct = Product(0, name, price, desc, img)
                listener?.onAddToBag(currentProduct)
            }

            btnWishlist.setOnClickListener {
                val currentProduct = Product(0, name, price, desc, img)
                listener?.onAddToWishlist(currentProduct)
            }

            btnSelectSize.setOnClickListener {
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
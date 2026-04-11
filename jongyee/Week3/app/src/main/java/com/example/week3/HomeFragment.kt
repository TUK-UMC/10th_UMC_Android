package com.example.week3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var navigator: NavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) navigator = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = listOf(
            Product(1, "Air Jordan XXXVI", "US$185", "Latest performance basketball shoe.", R.drawable.jordan),
            Product(2, "Nike Dunk Low", "US$110", "A hoop icon returns with classic colors.", R.drawable.airforce)
        )

        binding.homeRvProducts.apply {
            adapter = ProductAdapter(products) { product ->
                navigator?.onNavigateToDetail(product)
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
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
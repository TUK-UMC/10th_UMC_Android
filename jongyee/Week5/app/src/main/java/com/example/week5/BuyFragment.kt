package com.example.week5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week5.databinding.FragmentBuyBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class TopsFragment : Fragment()
class SaleFragment : Fragment()

class BuyFragment : Fragment() {
    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val productAdapter by lazy {
        ProductAdapter(
            productList = emptyList(),
            isGrid = true,
            onHeartClick = { product -> sharedViewModel.toggleFavorite(product) },
            onItemClick = { product -> sharedViewModel.requestNavigateToDetail(product) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buyRvProducts.adapter = productAdapter
        binding.buyRvProducts.layoutManager = GridLayoutManager(context, 2)

        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.buyProducts.collect { products ->
                productAdapter.updateData(products)
            }
        }

        binding.buyTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showRecyclerView()
                    1 -> showChildFragment(TopsFragment())
                    2 -> showChildFragment(SaleFragment())
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun showRecyclerView() {
        binding.buyRvProducts.visibility = View.VISIBLE
        binding.buyChildContainer.visibility = View.GONE
    }

    private fun showChildFragment(fragment: Fragment) {
        binding.buyRvProducts.visibility = View.GONE
        binding.buyChildContainer.visibility = View.VISIBLE
        childFragmentManager.beginTransaction().replace(R.id.buy_child_container, fragment).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

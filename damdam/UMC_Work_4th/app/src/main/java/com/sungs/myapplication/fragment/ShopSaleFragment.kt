package com.sungs.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sungs.myapplication.R

class ShopSaleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return TextView(requireContext()).apply {
            text = context.getString(R.string.Sale_text)
            textSize = 20f
            gravity = android.view.Gravity.CENTER
        }
    }
}
package com.example.shoppingapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.shoppingapp.News
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.MainAdapter
import com.example.shoppingapp.adapters.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerFragmentAdapter: ViewPagerFragmentAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager2 = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)

        viewPager2.adapter = viewPagerFragmentAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            if (position == 0){
                tab.text = "Shop"
            }
            else {
                tab.text = "Cart"
            }
        }.attach()


    }


}
package com.example.shoppingapp.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.shoppingapp.R
import com.example.shoppingapp.adapters.ViewPagerFragmentAdapter
import com.example.shoppingapp.databinding.FragmentAboutAppBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AboutAppFragment: Fragment(R.layout.fragment_about_app) {

    private lateinit var binding: FragmentAboutAppBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerFragmentAdapter: ViewPagerFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentAboutAppBinding.bind(view)
        binding.profileBar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()

        }
        viewPager2 = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this@AboutAppFragment)
        viewPager2.adapter = viewPagerFragmentAdapter


        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            if (position == 0){
                tab.text = "About app"
            }
            else {
                tab.text = "Authors"
            }
        }.attach()

    }
}
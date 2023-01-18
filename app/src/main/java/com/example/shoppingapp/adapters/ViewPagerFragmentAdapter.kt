package com.example.shoppingapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shoppingapp.fragments.AboutAppFragment
import com.example.shoppingapp.fragments.FirstNestedFragment
import com.example.shoppingapp.fragments.HomeFragment
import com.example.shoppingapp.fragments.SecondNestedFragment

class ViewPagerFragmentAdapter(activity: AboutAppFragment): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return FirstNestedFragment()
        } else {
            return SecondNestedFragment()
        }
    }

}
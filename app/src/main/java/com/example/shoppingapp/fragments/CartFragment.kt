package com.example.shoppingapp.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.shoppingapp.News
import com.example.shoppingapp.R
import com.example.shoppingapp.SharedViewModel
import com.example.shoppingapp.databinding.FragmentCartBinding

class CartFragment: Fragment(R.layout.fragment_cart) {
    private lateinit var imagesList: ArrayList<Int>
    private lateinit var heading: ArrayList<String>
    private lateinit var prices: ArrayList<Int>

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentCartBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentCartBinding.bind(view)
        binding.profileBar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()

        }
        val viewModel = ViewModelProvider(this@CartFragment).get(SharedViewModel::class.java)



    }

}
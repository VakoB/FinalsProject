package com.example.shoppingapp.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentCartBinding

class CartFragment: Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentCartBinding.bind(view)
        binding.profileBar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()

            val bundle = arguments
            val heading = bundle?.getString("heading")
            val image = bundle?.getInt("imageId")
            val news = bundle?.getString("news")
            val prices = bundle?.getInt("prices")

            if (image != null) {
                binding.rvImages.setImageResource(image)
            }




        }




    }
}
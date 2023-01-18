package com.example.shoppingapp.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentSettingsBinding.bind(view)
        binding.profileBar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()

        }


    }
}
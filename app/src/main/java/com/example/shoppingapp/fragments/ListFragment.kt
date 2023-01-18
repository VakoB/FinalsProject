package com.example.shoppingapp.fragments

import android.content.Context

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentAboutAppBinding
import com.example.shoppingapp.databinding.FragmentListBinding

class ListFragment: Fragment(R.layout.fragment_list) {
    private lateinit var inputItems: EditText
    private lateinit var wishList: TextView
    private lateinit var add: TextView
    private lateinit var binding: FragmentListBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentListBinding.bind(view)
        binding.profileBar.setNavigationOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_listFragment_to_centerFragment)
        }

        inputItems = view.findViewById(R.id.inputItems)
        wishList = view.findViewById(R.id.wishList)
        add = view.findViewById(R.id.addButton)

        val sharedPreferences = context?.getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
        val prefNote = sharedPreferences?.getString("NOTE", "")
        wishList.text = prefNote

        add.setOnClickListener {
            var note = inputItems.text.toString()
            if (note.isNullOrEmpty()) {
                return@setOnClickListener
            }


            var notes = wishList.text.toString()
            val resultText = notes + "\n" + note
            wishList.text = resultText
            inputItems.setText("")

            val editor = sharedPreferences?.edit()
            editor?.putString("NOTE", resultText)
            editor?.apply()


        }
        binding.deleteButton.setOnClickListener {
            val sharedPreferences = context?.getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.remove("NOTE")
            editor?.apply()
            wishList.text = ""
        }


    }




}
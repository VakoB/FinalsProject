package com.example.shoppingapp.fragments

import android.os.Bundle
import android.os.health.UidHealthStats
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.snap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.*
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.databinding.FragmentProfileBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding

    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var uid: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentProfileBinding.bind(view)
        binding.profileBar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.getReference("Users")


        getData()
        sharedViewModel.uid.observe(viewLifecycleOwner,{
            uid = it
        })

    }

    private fun getData() {
        databaseReference?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("ooooo","onDataChange: ${snapshot.child(uid).child("username")}")
                val uidUsername = snapshot.child(uid).child("username").value
                val username = uidUsername

                val uidPassword = snapshot.child(uid).child("password").value
                val password = uidPassword

                val uidEmail = snapshot.child(uid).child("email").value
                val email = uidEmail



                binding.tvUsername.text = username.toString()
                binding.tvPassword.text = password.toString()
                binding.tvEmail.text = email.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ooooo", "onCancelled:${error.toException()}")
            }

        })

        binding.changePass.setOnClickListener {

            findNavController().navigate(R.id.changePasswordFragment)

        }

    }









}
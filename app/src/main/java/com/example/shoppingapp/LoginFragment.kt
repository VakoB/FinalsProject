package com.example.shoppingapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginFragment: Fragment(R.layout.fragment_login) {
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginSubmit: TextView
    private lateinit var loginCreateAccount: TextView
    private lateinit var loginForgotPassword: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (FirebaseAuth.getInstance().currentUser != null){
            findNavController().navigate(R.id.centerFragment)
        }
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        loginEmail = view.findViewById(R.id.loginEmail)
        loginPassword = view.findViewById(R.id.loginPassword)
        loginCreateAccount = view.findViewById(R.id.createAccount)
        loginSubmit = view.findViewById(R.id.loginSubmit)
        loginForgotPassword = view.findViewById(R.id.loginForgotPass)
//__________________________________________________________________________________________________
        loginSubmit.setOnClickListener {
            val loginEmail = loginEmail.text.toString()
            val loginPassword = loginPassword.text.toString()

            if (loginEmail.isNotEmpty() && loginEmail.length < 30 && " " !in loginEmail
                && loginPassword.isNotEmpty() && loginPassword.length < 15 && " " !in loginPassword)
            {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(loginEmail,loginPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(requireContext(),"Welcome Back!",Toast.LENGTH_SHORT).show()
                            val action = LoginFragmentDirections.actionLoginFragmentToCenterFragment()
                            findNavController().navigate(action)

                        }
                        else{
                            androidx.appcompat.app.AlertDialog.Builder(requireContext())                       //fragmentshi requirecontext unda chavwerot
                                .setTitle("შეცდომა!")
                                .setMessage("სწორად შეიყვანეთ მონაცემები")
                                .setPositiveButton("გასაგებია") { dialog, i ->

                                }
                                .show()
                                .setCancelable(false)
                        }
                    }
            }
        }
        loginCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }
        loginForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.forgotPasswordFragment)
        }



    }
}
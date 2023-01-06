package com.example.shoppingapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment: Fragment(R.layout.fragment_login) {
    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginSubmit: TextView
    private lateinit var loginCreateAccount: TextView
    private lateinit var loginForgotPassword: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginUsername = view.findViewById(R.id.loginUsername)
        loginPassword = view.findViewById(R.id.loginPassword)
        loginCreateAccount = view.findViewById(R.id.createAccount)
        loginSubmit = view.findViewById(R.id.loginSubmit)
        loginForgotPassword = view.findViewById(R.id.loginForgotPass)
//---------------------------------------------------------------------------------------------------------------------------------------
        loginSubmit.setOnClickListener {
            val loginUsername = loginUsername.text.toString()
            val loginPassword = loginPassword.text.toString()

            if (loginUsername.isNotEmpty() && loginUsername.length < 30 && " " !in loginUsername
                && loginPassword.isNotEmpty() && loginPassword.length < 15 && " " !in loginPassword)
            {
                Toast.makeText(requireContext(),"Welcome!",Toast.LENGTH_SHORT).show()
                //todo: აქედან გადავალთ მთავარ ფრაგმენტზე + აქ firebase-ის აუთენთიფიკაციას და realtime Database-ს ვიყენებ
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
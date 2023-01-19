package com.example.shoppingapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text


class ForgotPasswordFragment: Fragment(R.layout.fragment_forgot_password) {
    private lateinit var auth: FirebaseAuth
    private lateinit var submit: TextView
    private lateinit var etpassword: EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        submit = view.findViewById(R.id.forgotSubmit)
        etpassword = view.findViewById(R.id.forgotEmail)
        auth = FirebaseAuth.getInstance()
        submit.setOnClickListener {

            val passwordField = etpassword
            val newPassword = passwordField.text.toString()
            changePassword(newPassword)
            val sPassword = etpassword.text.toString()
            auth.sendPasswordResetEmail(sPassword)
                .addOnCompleteListener {
                    val auth = FirebaseAuth.getInstance()
                    val user1 = auth.currentUser

                    Toast.makeText(requireContext(),"Please check your E-mail",Toast.LENGTH_SHORT).show()
                    val user = FirebaseAuth.getInstance().currentUser
                    val database = FirebaseDatabase.getInstance()
                    val myRef = user?.let { it1 -> database.getReference("Users").child(it1.uid) }
                    if (myRef != null) {
                        myRef.child("password").setValue(sPassword)
                    }
                    

                }.addOnFailureListener {
                    Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
                }


        }


    }

    /*fun changePassword(newPassword: String) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.updatePassword(newPassword)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updatePasswordInRealtimeDatabase(newPassword, user.uid)
                    //password has been successfully changed
                } else {
                    //password change failed
                }
            }
    }*/
}
package com.example.shoppingapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.databinding.FragmentChangePasswordBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ChangePasswordFragment: Fragment(R.layout.fragment_change_password) {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentChangePasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentChangePasswordBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        binding.submitChange.setOnClickListener {

            changePassword()

        }




    }

    private fun changePassword() {
        if (binding.loginOldPassword.text.toString().isNotEmpty()
            && binding.loginNewPassword.text.toString().isNotEmpty()
            && binding.loginRepeatNewPassword.text.toString().isNotEmpty()){

            if (binding.loginNewPassword.text.toString().equals(binding.loginRepeatNewPassword.text.toString())){
                val user = auth.currentUser
                if (user != null && user.email != null){
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, binding.loginOldPassword.text.toString())


                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Toast.makeText(requireContext(),"Successful Re-authentification",Toast.LENGTH_SHORT).show()
                                user!!.updatePassword(binding.loginNewPassword.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            val newPassword = binding.loginNewPassword.text.toString()
                                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                                            val passwordRef = FirebaseDatabase.getInstance().getReference("Users/$userId/password")

                                            passwordRef.setValue(newPassword)
                                                .addOnCompleteListener { task ->
                                                    if (task.isSuccessful) {
                                                        Log.d(TAG, "Password updated in database.")
                                                    } else {
                                                        Log.d(TAG, "Error updating password in database.", task.exception)
                                                    }
                                                }
                                            Toast.makeText(requireContext(),"Password changed successfully",Toast.LENGTH_SHORT).show()
                                            auth.signOut()

                                            findNavController().navigate(R.id.loginFragment)

                                        }
                                    }
                            }
                            else{
                                Toast.makeText(requireContext(),"Failed Re-authentification",Toast.LENGTH_SHORT).show()
                            }

                        }


                }

            }
            else{
                Toast.makeText(requireContext(),"Passwords aren't matching",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.loginFragment)
            }


        }
        else{
            Toast.makeText(requireContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show()
        }
    }
}
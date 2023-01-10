package com.example.shoppingapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationFragment: Fragment(R.layout.fragment_registration) {
    private lateinit var registerUsername: EditText
    private lateinit var registerEmail: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerSubmit: TextView
    private lateinit var registerToLogin: TextView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        registerUsername = view.findViewById(R.id.registerUsername)
        registerEmail = view.findViewById(R.id.registerEmail)
        registerPassword = view.findViewById(R.id.registerPassword)
        registerSubmit = view.findViewById(R.id.registerSubmit)
        registerToLogin = view.findViewById(R.id.register_to_login)


        registerSubmit.setOnClickListener {
            val registerUsername = registerUsername.text.toString()
            val registerEmail = registerEmail.text.toString()
            val registerPassword = registerPassword.text.toString()
            if (registerEmail.isNotEmpty() && registerEmail.length < 30 && " " !in registerEmail
                && registerPassword.isNotEmpty() && registerPassword.length < 15 && " " !in registerPassword)
            {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(registerEmail,registerPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            val user = User(registerEmail,registerPassword,registerUsername)
                            if (uid != null){
                                databaseReference.child(uid).setValue(user).addOnCompleteListener {
                                    if (it.isSuccessful){
                                        Toast.makeText(requireContext(),"Welcome!",Toast.LENGTH_SHORT).show()
                                        findNavController().navigate(R.id.mainFragment)
                                        //todo: მომხმარებლის მონაცემები უნდა გადავიტანო(ალბათ shared prefrences)
                                    }
                                }
                            }
                            else{
                                Toast.makeText(requireContext(),"uid is null",Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(requireContext(),"Error!",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        registerToLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }


}
package com.example.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shoppingapp.fragments.AboutAppFragment
import com.example.shoppingapp.fragments.HomeFragment
import com.example.shoppingapp.fragments.ProfileFragment
import com.example.shoppingapp.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadFragment(HomeFragment())
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())

                }

                R.id.settingsFragment -> {
                    loadFragment(SettingsFragment())

                }
                R.id.aboutAppFragment -> {
                    loadFragment(AboutAppFragment())

                }
                R.id.profileFragment -> {
                    loadFragment(ProfileFragment())

                }
                else -> {

                }

            }
            true
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.navHostFragment,fragment)
        transaction.commit()
    }
}

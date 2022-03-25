package com.practiclewatheraapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practiclewatheraapp.R
import androidx.navigation.ui.NavigationUI


import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController: NavController =
            Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        setupWithNavController(bottomNavigationView, navController)
    }
}
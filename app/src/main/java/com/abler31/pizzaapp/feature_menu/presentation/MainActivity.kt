package com.abler31.pizzaapp.feature_menu.presentation

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.abler31.pizzaapp.R
import com.abler31.pizzaapp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val toolbar = findViewById<MaterialToolbar>(R.id.appToolbar)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        setSupportActionBar(toolbar)
        navView.setupWithNavController(navController)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}
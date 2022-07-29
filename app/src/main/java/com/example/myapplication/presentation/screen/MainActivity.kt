package com.example.myapplication.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presentation.viewModel.ViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    val viewModel: ViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.dataState.observe(this){
            binding.progressBar.isVisible = it.loading
            if (it.error){
                Snackbar.make(binding.root,"Произошла ошибка",Snackbar.LENGTH_INDEFINITE)
                    .setAction("Повторить"){
                        viewModel.load()
                    }
                    .show()
            }
        }
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
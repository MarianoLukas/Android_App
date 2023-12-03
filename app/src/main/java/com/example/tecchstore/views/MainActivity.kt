package com.example.tecchstore.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.tecchstore.R
import com.example.tecchstore.db.AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container) as NavHostFragment
//        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(androidx.navigation.fragment.ktx.R.id.nav_host_fragment_container)
//        val navController = this.findNavController()
//        return navController.navigateUp()
//    }
}
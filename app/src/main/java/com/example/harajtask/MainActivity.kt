package com.example.harajtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavGraph()
    }

    fun updateToolBar(actionBarTitle: String, enableBack: Boolean = false) {
        supportActionBar?.apply {
            title = actionBarTitle
            elevation = 0f
            setDisplayHomeAsUpEnabled(enableBack)
        }
        invalidateOptionsMenu()
    }

    private fun initNavGraph() {
        navController = findNavController(R.id.fragmentContainer)
        navGraph = navController.navInflater.inflate(R.navigation.nav_home)
    }
}
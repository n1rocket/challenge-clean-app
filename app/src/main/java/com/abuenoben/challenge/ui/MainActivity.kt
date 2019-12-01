package com.abuenoben.challenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.abuenoben.challenge.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.fragment).navigateUp()
        return super.onSupportNavigateUp()
    }

}

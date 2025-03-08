package com.ibrahim.helyxonstoreapptask.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.ibrahim.helyxonstoreapptask.R
import com.ibrahim.helyxonstoreapptask.databinding.ActivityMainBinding
import com.ibrahim.helyxonstoreapptask.view.fragments.ProductListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productListFragment = ProductListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, productListFragment)
            .commit()
    }
}
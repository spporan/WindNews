package com.windnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.windnews.R
import com.windnews.databinding.ActivityMainBinding
import com.windnews.ui.fragments.SplashFragment
import com.windnews.ui.viewmodels.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        savedInstanceState ?: openSplash()
    }
    
    private fun openSplash() {

        val splashFragment = SplashFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, splashFragment, SplashFragment.TAG)
            .commit()
    }
}
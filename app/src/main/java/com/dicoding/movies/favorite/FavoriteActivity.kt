package com.dicoding.movies.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.movies.R
import com.dicoding.movies.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPageAdapter = SectionPageAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionPageAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f


    }
}
package com.dicoding.mymovielist.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.adapter.TabLayoutAdapter
import com.dicoding.mymovielist.databinding.ActivityMainBinding
import com.dicoding.mymovielist.favorite.FavoriteActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tvShows
        )
    }

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        title = "MovieVerse"

        val bundle = Bundle()

        val sectionsPagerAdapter = TabLayoutAdapter(this, bundle)
        activityMainBinding.viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, activityMainBinding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.favorite){
            val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
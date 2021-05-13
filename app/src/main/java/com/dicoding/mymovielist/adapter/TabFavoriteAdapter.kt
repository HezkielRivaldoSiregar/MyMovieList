package com.dicoding.mymovielist.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.mymovielist.favorite.MovieFavoriteFragment
import com.dicoding.mymovielist.favorite.ShowsFavoriteFragment

class TabFavoriteAdapter(activity: AppCompatActivity, data: Bundle) :
    FragmentStateAdapter(activity) {

    private var fragmentBundle: Bundle = data

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFavoriteFragment()
            1 -> ShowsFavoriteFragment()
            else -> Fragment()
        }.also {
            it.arguments = this.fragmentBundle
        }
    }

    override fun getItemCount() = 2
}
package com.dicoding.mymovielist.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.mymovielist.main.MoviesFragment
import com.dicoding.mymovielist.main.ShowsFragment

class TabLayoutAdapter(activity: AppCompatActivity, data: Bundle) :
    FragmentStateAdapter(activity) {

    private var fragmentBundle: Bundle = data

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MoviesFragment()
            1 -> ShowsFragment()
            else -> Fragment()
        }.also {
            it.arguments = this.fragmentBundle
        }
    }

    override fun getItemCount() = 2
}
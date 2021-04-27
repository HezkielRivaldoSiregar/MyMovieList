package com.dicoding.mymovielist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovielist.adapter.ListTvShowsAdapter
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.databinding.FragmentMoviesShowsBinding

class ShowsFragment : Fragment(R.layout.fragment_movies_shows) {

    private lateinit var fragmentTvShowsBinding: FragmentMoviesShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowsBinding = FragmentMoviesShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MoviesShowsViewModel::class.java]
            val tvShows = viewModel.getTvShowsData()

            val moviesAdapter = ListTvShowsAdapter()
            moviesAdapter.setTvShows(tvShows)

            if(tvShows.size > 0) showLoading(false)

            with(fragmentTvShowsBinding.rvShowsMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentTvShowsBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentTvShowsBinding.progressBar.visibility = View.GONE
        }
    }

}
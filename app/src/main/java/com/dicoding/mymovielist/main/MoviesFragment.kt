package com.dicoding.mymovielist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovielist.adapter.ListMoviesAdapter
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.databinding.FragmentMoviesShowsBinding

class MoviesFragment : Fragment(R.layout.fragment_movies_shows) {

    private lateinit var fragmentMoviesBinding: FragmentMoviesShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = FragmentMoviesShowsBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MoviesShowsViewModel::class.java]
            val movies = viewModel.getMoviesData()

            val moviesAdapter = ListMoviesAdapter()
            moviesAdapter.setMovies(movies)
            if(movies.isNotEmpty()) showLoading(false)

            with(fragmentMoviesBinding.rvShowsMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMoviesBinding.progressBar.visibility = View.GONE
        }
    }

}
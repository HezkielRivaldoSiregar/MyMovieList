package com.dicoding.mymovielist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovielist.adapter.ListMoviesAdapter
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.ViewModelFactory
import com.dicoding.mymovielist.databinding.FragmentMoviesShowsBinding
import com.dicoding.mymovielist.vo.Status

class MoviesFragment : Fragment(R.layout.fragment_movies_shows) {

    private lateinit var fragmentMoviesBinding: FragmentMoviesShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = FragmentMoviesShowsBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesShowsViewModel::class.java]
            val moviesAdapter = ListMoviesAdapter()

            showLoading(true)
            viewModel.getMoviesData().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            moviesAdapter.submitList(movies.data)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(
                                context,
                                resources.getString(R.string.error),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })

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
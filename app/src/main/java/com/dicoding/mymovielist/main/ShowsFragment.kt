package com.dicoding.mymovielist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.ViewModelFactory
import com.dicoding.mymovielist.adapter.ListTvShowsAdapter
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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesShowsViewModel::class.java]
            val showsAdapter = ListTvShowsAdapter()

            showLoading(true)
            viewModel.getTvShowsData().observe(viewLifecycleOwner, { shows ->
                showLoading(false)
                showsAdapter.setTvShows(shows)
                showsAdapter.notifyDataSetChanged()
            })

            with(fragmentTvShowsBinding.rvShowsMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = showsAdapter
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

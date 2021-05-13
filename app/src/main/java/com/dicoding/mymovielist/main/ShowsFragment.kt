package com.dicoding.mymovielist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.ViewModelFactory
import com.dicoding.mymovielist.adapter.ListTvShowsAdapter
import com.dicoding.mymovielist.databinding.FragmentMoviesShowsBinding
import com.dicoding.mymovielist.vo.Status

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

            viewModel.getTvShowsData().observe(viewLifecycleOwner, { shows ->
                if (shows != null) {
                    when (shows.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            showsAdapter.submitList(shows.data)
                            showsAdapter.notifyDataSetChanged()
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

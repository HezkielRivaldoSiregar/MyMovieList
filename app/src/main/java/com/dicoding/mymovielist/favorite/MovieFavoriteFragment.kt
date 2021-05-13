package com.dicoding.mymovielist.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.ViewModelFactory
import com.dicoding.mymovielist.adapter.ListFavoriteMovieAdapter
import com.dicoding.mymovielist.databinding.FragmentMovieShowFavoriteBinding

class MovieFavoriteFragment : Fragment(R.layout.fragment_movie_show_favorite){

    private lateinit var binding : FragmentMovieShowFavoriteBinding
    private lateinit var adapter : ListFavoriteMovieAdapter
    private lateinit var viewModel : FavoriteMovieShowsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieShowFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieShowsViewModel::class.java]

            adapter = ListFavoriteMovieAdapter()
            showLoading(true)
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                showLoading(false)
                adapter.submitList(movies)
            })

            with(binding.rvShowsMovies) {
                layoutManager = LinearLayoutManager(context)
                adapter = this@MovieFavoriteFragment.adapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
package com.dicoding.mymovielist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.databinding.ItemRowBinding
import com.dicoding.mymovielist.detail.MoviesDetailActivity

class ListMoviesAdapter : PagedListAdapter<Movies, ListMoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movies>() {

            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemRowBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    class MoviesViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movies) {
            with(binding) {
                tvTitle.text = movies.title
                tvDesc.text = movies.overview

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MoviesDetailActivity::class.java)
                    intent.putExtra(MoviesDetailActivity.EXTRA_TITLE, movies.title)
                    intent.putExtra(MoviesDetailActivity.EXTRA_ID, movies.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(movies.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image)
                        .error(R.drawable.ic_error))
                    .into(itemImage)
            }
        }
    }

}
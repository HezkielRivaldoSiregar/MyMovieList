package com.dicoding.mymovielist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.data.Movies
import com.dicoding.mymovielist.databinding.ItemRowBinding
import com.dicoding.mymovielist.detail.MoviesDetailActivity

class ListMoviesAdapter : RecyclerView.Adapter<ListMoviesAdapter.MoviesViewHolder>() {

    private val listMovies = ArrayList<Movies>()

    fun setMovies(movies: List<Movies>?){
        if(movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemRowBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    class MoviesViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movies) {
            with(binding) {
                tvTitle.text = movies.title
                tvDesc.text = movies.overview

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MoviesDetailActivity::class.java)
                    intent.putExtra(MoviesDetailActivity.EXTRA_TITLE, movies.title)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(movies.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image)
                        .error(R.drawable.ic_error))
                    .into(itemImage)
            }
        }
    }

}
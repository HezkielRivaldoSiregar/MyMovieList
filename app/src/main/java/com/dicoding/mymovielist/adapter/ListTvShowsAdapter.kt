package com.dicoding.mymovielist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.data.TvShows
import com.dicoding.mymovielist.databinding.ItemRowBinding
import com.dicoding.mymovielist.detail.MoviesDetailActivity
import com.dicoding.mymovielist.detail.ShowsDetailActivity

class ListTvShowsAdapter: RecyclerView.Adapter<ListTvShowsAdapter.TvShowsViewHolder>() {

    private val listTvShows = ArrayList<TvShows>()

    fun setTvShows(tvShows: List<TvShows>?) {
        if (tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemRowBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShows = listTvShows[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listTvShows.size

    class TvShowsViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShows) {
            with(binding) {
                tvTitle.text = tvShows.title
                tvDesc.text = tvShows.overview

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ShowsDetailActivity::class.java)
                    intent.putExtra(MoviesDetailActivity.EXTRA_TITLE, tvShows.title)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvShows.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image)
                            .error(R.drawable.ic_error)
                    )
                    .into(itemImage)
            }
        }
    }

}
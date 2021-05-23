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
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.databinding.ItemRowBinding
import com.dicoding.mymovielist.detail.ShowsDetailActivity

class ListFavoriteShowAdapter : PagedListAdapter<TvShows, ListFavoriteShowAdapter.FavoriteShowViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShows>() {

            override fun areItemsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteShowViewHolder {
        val itemRowBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteShowViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(
        holder: ListFavoriteShowAdapter.FavoriteShowViewHolder,
        position: Int
    ) {
        val shows = getItem(position)
        if (shows != null) {
            holder.bind(shows)
        }
    }

    inner class FavoriteShowViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shows: TvShows) {
            with(binding) {
                tvTitle.text = shows.title
                tvDesc.text = shows.overview

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ShowsDetailActivity::class.java)
                    intent.putExtra(ShowsDetailActivity.EXTRA_TITLE, shows.title)
                    intent.putExtra(ShowsDetailActivity.EXTRA_ID, shows.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(shows.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image)
                            .error(R.drawable.ic_error))
                    .into(itemImage)
            }
        }
    }
}
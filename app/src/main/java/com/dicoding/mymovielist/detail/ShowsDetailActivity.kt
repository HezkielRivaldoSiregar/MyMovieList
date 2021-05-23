package com.dicoding.mymovielist.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.ViewModelFactory
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.databinding.ActivityShowsDetailBinding
import com.dicoding.mymovielist.main.MainActivity
import com.dicoding.mymovielist.vo.Status

class ShowsDetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityShowsDetailBinding
    private lateinit var viewModel: MoviesShowsDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MoviesShowsDetailViewModel::class.java]

        val extras = intent.extras
        if(extras!=null){
            val title = extras.getString(EXTRA_TITLE)
            val showId = extras.getInt(EXTRA_ID)

            setTitle(title)
            viewModel.setSelectedShows(showId)
            viewModel.tvShow.observe(this, { shows ->
                when (shows.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> if (shows.data != null) {
                        showLoading(false)
                        populateShows(shows.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(applicationContext, resources.getString(R.string.error), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun populateShows(shows: TvShows){
        val state = shows.favorited
        setFavorite(state)
        binding.tvTitle.text = shows.title
        binding.tvOverview.text = shows.overview
        binding.tvReleaseDate.text = shows.releaseDate
        binding.tvGenre.text = shows.genre
        binding.tvSeason.text = shows.seasons
        binding.tvDuration.text = shows.duration
        Glide.with(this)
            .load(shows.image)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_image)
                    .error(R.drawable.ic_error))
            .into(binding.itemImage)
        Glide.with(this)
            .load(shows.image)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_image)
                    .error(R.drawable.ic_error))
            .into(binding.itemBackdrop)

        binding.btnTrailer.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(shows.trailer))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage("com.google.android.youtube")
            startActivity(intent)
        }
        binding.floatingFavorite.setOnClickListener{
            state != state
            setFavorite(state)
            viewModel.setFavorite()
            if(!state){
                val toast = Toast.makeText(applicationContext, "\"${title}\" Added to Favorite", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                val toast = Toast.makeText(applicationContext, "\"${title}\" Removed to Favorite", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if(state){
            binding.floatingFavorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_true)
            )
        }else{
            binding.floatingFavorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_false)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_detail, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = intent.getStringExtra(EXTRA_TITLE)
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this@ShowsDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.share){
            val share = Intent()
            share.action = Intent.ACTION_SEND
            share.type = "text/plain"
            share.putExtra(
                Intent.EXTRA_TEXT,
                "Hello I suggest you to watch \"${title}\". It's a great tv show "
            )
            startActivity(Intent.createChooser(share, "Share This Tv Show to"))
            val toast = Toast.makeText(
                applicationContext,
                "You are Sharing \"${title}\"",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.apply {
                progressBar.visibility = View.VISIBLE
                tvOverview.visibility = View.GONE
                tvGenre.visibility = View.GONE
                tvReleaseDate.visibility = View.GONE
                tvTitle.visibility = View.GONE
                tvSeason.visibility = View.GONE
                tvDuration.visibility = View.GONE
                durationTv.visibility = View.GONE
                genreTv.visibility = View.GONE
                releaseDateTv.visibility = View.GONE
                itemBackdrop.visibility = View.GONE
                itemImage.visibility = View.GONE
                btnTrailer.visibility = View.GONE
                floatingFavorite.visibility = View.GONE
            }
        } else {
            binding.apply {
                progressBar.visibility = View.GONE
                tvOverview.visibility = View.VISIBLE
                tvGenre.visibility = View.VISIBLE
                tvReleaseDate.visibility = View.VISIBLE
                tvTitle.visibility = View.VISIBLE
                tvSeason.visibility = View.VISIBLE
                tvDuration.visibility = View.VISIBLE
                durationTv.visibility = View.VISIBLE
                genreTv.visibility = View.VISIBLE
                releaseDateTv.visibility = View.VISIBLE
                itemBackdrop.visibility = View.VISIBLE
                itemImage.visibility = View.VISIBLE
                btnTrailer.visibility = View.VISIBLE
                floatingFavorite.visibility = View.VISIBLE
            }
        }
    }
}
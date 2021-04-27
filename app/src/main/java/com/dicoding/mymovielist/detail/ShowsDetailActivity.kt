package com.dicoding.mymovielist.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.data.TvShows
import com.dicoding.mymovielist.databinding.ActivityShowsDetailBinding
import com.dicoding.mymovielist.main.MainActivity

class ShowsDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_TITLE = "extra_title"
    }

    private lateinit var binding: ActivityShowsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ShowsDetailViewModel::class.java]
        val extras = intent.extras
        if(extras!=null){
            val title = extras.getString(EXTRA_TITLE)
            setTitle(title)
            if(title != null){
                viewModel.setSelectedShows(title)
                populateShows(viewModel.getShows())
            }
        }
    }

    private fun populateShows(shows: TvShows){
        binding.tvTitle.text = shows.title
        binding.tvOverview.text = shows.overview
        binding.tvCreator.text = shows.creator
        binding.tvRating.text = shows.rating
        binding.tvReleaseDate.text = shows.releaseDate
        binding.tvGenre.text = shows.genre
        binding.tvStatus.text = shows.status
        binding.tvDuration.text = shows.duration
        binding.tvSeasons.text = shows.seasons
        Glide.with(this)
            .load(shows.image)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_image)
                    .error(R.drawable.ic_error))
            .into(binding.itemImage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this@ShowsDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
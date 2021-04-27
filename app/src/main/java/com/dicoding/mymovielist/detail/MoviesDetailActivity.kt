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
import com.dicoding.mymovielist.data.Movies
import com.dicoding.mymovielist.databinding.ActivityMoviesDetailBinding
import com.dicoding.mymovielist.main.MainActivity

class MoviesDetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TITLE = "extra_title"
    }

    private lateinit var binding: ActivityMoviesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MoviesDetailViewModel::class.java]

        val extras = intent.extras
        if(extras!=null){
            val title = extras.getString(EXTRA_TITLE)
            setTitle(title)
            if(title != null){
                viewModel.setSelectedMovies(title)
                populateMovies(viewModel.getMovies())
            }
        }
    }

    private fun populateMovies(movies: Movies){
        binding.tvTitle.text = movies.title
        binding.tvOverview.text = movies.overview
        binding.tvDirector.text = movies.director
        binding.tvRating.text = movies.rating
        binding.tvReleaseDate.text = movies.releaseDate
        binding.tvGenre.text = movies.genre
        binding.tvStatus.text = movies.status
        binding.tvDuration.text = movies.duration
        Glide.with(this)
            .load(movies.image)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_image)
                .error(R.drawable.ic_error))
            .into(binding.itemImage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this@MoviesDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}
package com.dicoding.mymovielist.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovielist.R
import com.dicoding.mymovielist.ViewModelFactory
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.databinding.ActivityMoviesDetailBinding
import com.dicoding.mymovielist.main.MainActivity

class MoviesDetailActivity : AppCompatActivity(){

    companion object{
        const val EXTRA_TITLE = "extra_title"
    }

    private lateinit var binding: ActivityMoviesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[MoviesShowsDetailViewModel::class.java]

        val extras = intent.extras
        if(extras!=null){
            val title = extras.getString(EXTRA_TITLE)
            setTitle(title)
            if(title != null){
                viewModel.setSelectedMovies(title)
                viewModel.getMovie().observe(this, { movie: List<Movies> ->
                    val titleIdx = movie.indexOfFirst{it.title == title}
                    populateMovies(movie[titleIdx])
                })
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_detail, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = intent.getStringExtra(ShowsDetailActivity.EXTRA_TITLE)
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this@MoviesDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.share){
            val share = Intent()
            share.action = Intent.ACTION_SEND
            share.type = "text/plain"
            share.putExtra(
                Intent.EXTRA_TEXT,
                "Hello I suggest you to watch \"${title}\". It's a great movie "
            )
            startActivity(Intent.createChooser(share, "Share This Movie to"))
            val toast = Toast.makeText(
                applicationContext,
                "You are Sharing \"${title}\"",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
        return super.onOptionsItemSelected(item)
    }

}
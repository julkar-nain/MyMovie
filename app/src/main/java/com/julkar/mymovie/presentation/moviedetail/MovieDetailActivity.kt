package com.julkar.mymovie.presentation.moviedetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.julkar.mymovie.R
import com.julkar.mymovie.databinding.ActivityMovieDetailBinding
import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail
import com.julkar.mymovie.util.EXTRA_CONTENT_TYPE
import com.julkar.mymovie.util.EXTRA_MOVIE
import com.julkar.mymovie.util.IMAGE_URL_BACKDROP
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieDetailViewModel

    private var wishList: Boolean = false

    private lateinit var viewBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        viewModel = ViewModelProvider(this, modelFactory).get(MovieDetailViewModel::class.java)

        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
        val type = intent.getSerializableExtra(EXTRA_CONTENT_TYPE) as ContentType

        viewModel.movieDetailState.observe(this) { state ->
            viewBinding.progressBar.visibility = View.GONE

            when (state) {
                is MovieDetailState.Success -> {
                    bindMovieDetail(state.movie)
                }

                is MovieDetailState.Failure -> {
                    Toast.makeText(this, getString(R.string.network_error_msg), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        viewModel.bindMovieDetailData(type, movie.id)
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.wish_list_menu, menu)

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val item = menu.findItem(R.id.wish_list)

        if (wishList) {
            item.title = getString(R.string.remove_from_wish_list)
        } else {
            item.title = getString(R.string.add_to_wish_list)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.wish_list -> {
                if (wishList) {
                    //remove from wish list
                } else {
                    //add to wish list
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bindMovieDetail(movieDetail: MovieDetail) {
        viewBinding.tvTitle.text = movieDetail.title ?: movieDetail.name
        viewBinding.tvOverview.text = movieDetail.overview

        Picasso.get()
            .load(IMAGE_URL_BACKDROP + movieDetail.posterPath)
            .into(viewBinding.image)
    }
}
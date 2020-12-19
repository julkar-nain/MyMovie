package com.julkar.mymovie.presentation.moviedetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.julkar.mymovie.MainApplication
import com.julkar.mymovie.R
import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail
import com.julkar.mymovie.util.EXTRA_CONTENT_TYPE
import com.julkar.mymovie.util.EXTRA_MOVIE
import com.julkar.mymovie.util.IMAGE_URL_BACKDROP
import com.julkar.mymovie.util.IMAGE_URL_POSTER
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        (application as MainApplication).appComponent.getMovieSubComponent().create().inject(this)

        viewModel = ViewModelProvider(this, modelFactory).get(MovieDetailViewModel::class.java)

        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
        val type = intent.getSerializableExtra(EXTRA_CONTENT_TYPE) as ContentType

        viewModel.movieDetailState.observe(this) { state ->
            progressBar.visibility = View.GONE

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
        progressBar.visibility = View.VISIBLE
    }

    private fun bindMovieDetail(movieDetail: MovieDetail) {
        tvTitle.text = movieDetail.title ?: movieDetail.name
        tvOverview.text = movieDetail.overview

        Picasso.get()
            .load(IMAGE_URL_BACKDROP + movieDetail.posterPath)
            .into(image)
    }
}
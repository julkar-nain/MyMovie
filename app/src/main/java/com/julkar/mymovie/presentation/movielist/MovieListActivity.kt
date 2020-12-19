package com.julkar.mymovie.presentation.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.julkar.mymovie.MainApplication
import com.julkar.mymovie.R
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        (application as MainApplication).appComponent.getMovieSubComponent().create().inject(this)

        viewModel = ViewModelProvider(this, modelFactory).get(MovieListViewModel::class.java)

        viewModel.movieState.observe(this) { movieState ->
            when (movieState) {
                is MovieState.Success -> {
                    val test = movieState.movieList
                }

                is MovieState.Failure -> {
                    Toast.makeText(this, getString(R.string.network_error_msg), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        viewModel.bindMovieListData(1)
    }
}
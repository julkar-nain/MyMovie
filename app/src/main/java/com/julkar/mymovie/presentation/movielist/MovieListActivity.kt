package com.julkar.mymovie.presentation.movielist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.julkar.mymovie.MainApplication
import com.julkar.mymovie.R
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.presentation.movielist.adapter.MovieAdapter
import com.julkar.mymovie.presentation.util.ListItemListener
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), ListItemListener<Movie> {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieAdapter: MovieAdapter<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        (application as MainApplication).appComponent.getMovieSubComponent().create().inject(this)

        viewModel = ViewModelProvider(this, modelFactory).get(MovieListViewModel::class.java)

        viewModel.movieState.observe(this) { movieState ->
            when (movieState) {
                is MovieState.Success -> {
                    movieAdapter.update(movieState.movieList)
                }

                is MovieState.Failure -> {
                    Toast.makeText(this, getString(R.string.network_error_msg), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        configureMovieList()

        viewModel.bindMovieListData(1)
    }

    private fun configureMovieList() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        movieAdapter = MovieAdapter(this, listOf())
        rvMovieList.layoutManager = layoutManager
        rvMovieList.adapter = movieAdapter
    }

    override fun onClick(view: View, position: Int, data: Movie) {
        Toast.makeText(this, "id : ${data.id} title: ${data.title}", Toast.LENGTH_SHORT).show()
    }
}
package com.julkar.mymovie.presentation.movielist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.julkar.mymovie.MainApplication
import com.julkar.mymovie.R
import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.presentation.moviedetail.MovieDetailActivity
import com.julkar.mymovie.presentation.movielist.adapter.MovieAdapter
import com.julkar.mymovie.presentation.util.LastItemListener
import com.julkar.mymovie.presentation.util.ListItemListener
import com.julkar.mymovie.util.EXTRA_CONTENT_TYPE
import com.julkar.mymovie.util.EXTRA_MOVIE
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieListViewModel

    private val movieAdapter by lazy {
        MovieAdapter( object : ListItemListener<Movie> {
            override fun onClick(view: View, position: Int, data: Movie) {
                 startActivity(data, ContentType.MOVIE)
            }

        }, object : LastItemListener {
            override fun lastItemClick() {
                movieListLoader.visibility = View.VISIBLE
                viewModel.bindMovieListData(ContentType.MOVIE, ++ContentType.MOVIE.page)
            }
        })
    }

    private val seriesAdapter by lazy {
        MovieAdapter(object : ListItemListener<Movie> {
            override fun onClick(view: View, position: Int, data: Movie) {
                startActivity(data, ContentType.TV_SERIES)
            }

        }, object : LastItemListener {
            override fun lastItemClick() {
                seriesListLoader.visibility = View.VISIBLE
                viewModel.bindMovieListData(ContentType.TV_SERIES, ++ContentType.TV_SERIES.page)
            }
        })
    }

    private val trendingAdapter by lazy {
        MovieAdapter<Movie>(itemClickListener = object : LastItemListener {
            override fun lastItemClick() {
                trendingListLoader.visibility = View.VISIBLE
                viewModel.bindMovieListData(ContentType.TRENDING, ++ContentType.TRENDING.page)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        (application as MainApplication).appComponent.getMovieSubComponent().create().inject(this)

        viewModel = ViewModelProvider(this, modelFactory).get(MovieListViewModel::class.java)

        viewModel.movieListState.observe(this) { movieState ->
            when (movieState) {
                is MovieListState.Success -> {
                    if (movieState.type == ContentType.MOVIE) {
                        movieListLoader.visibility = View.GONE
                        movieAdapter.submitList(
                            ListMerge(
                                movieAdapter.currentList,
                                movieState.movieList
                            )
                        )
                    } else if (movieState.type == ContentType.TV_SERIES) {
                        seriesListLoader.visibility = View.GONE
                        seriesAdapter.submitList(
                            ListMerge(
                                seriesAdapter.currentList,
                                movieState.movieList
                            )
                        )
                    } else if (movieState.type == ContentType.TRENDING) {
                        trendingListLoader.visibility = View.GONE
                        trendingAdapter.submitList(
                            ListMerge(
                                trendingAdapter.currentList,
                                movieState.movieList
                            )
                        )
                    }
                }

                is MovieListState.Failure -> {
                    hideLoader()
                    Toast.makeText(this, getString(R.string.network_error_msg), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        configureMovieList(movieAdapter, rvMovieList, LinearLayoutManager.HORIZONTAL)
        configureMovieList(seriesAdapter, rvSeriesList, LinearLayoutManager.HORIZONTAL)
        configureMovieList(trendingAdapter, rvTrendingList, LinearLayoutManager.HORIZONTAL)

        viewModel.bindMovieListData(ContentType.MOVIE, ContentType.MOVIE.page)
        movieListLoader.visibility = View.VISIBLE

        viewModel.bindMovieListData(ContentType.TV_SERIES, ContentType.TV_SERIES.page)
        seriesListLoader.visibility = View.VISIBLE

        viewModel.bindMovieListData(ContentType.TRENDING, ContentType.TRENDING.page)
        trendingListLoader.visibility = View.VISIBLE
    }

    private fun configureMovieList(
        adapter: MovieAdapter<Movie>,
        recyclerView: RecyclerView,
        orientation: Int
    ) {
        val layoutManager = LinearLayoutManager(this, orientation, false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun hideLoader() {
        movieListLoader.visibility = View.GONE
        seriesListLoader.visibility = View.GONE
        trendingListLoader.visibility = View.GONE
    }

    private fun startActivity(movie: Movie, type: ContentType) {
        Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, movie)
        intent.putExtra(EXTRA_CONTENT_TYPE, type)

        startActivity(intent)
    }

    private fun <T> ListMerge(first: List<T>, second: List<T>): List<T> {
        val list: MutableList<T> = ArrayList()
        list.addAll(first)
        list.addAll(second)

        return list
    }
}
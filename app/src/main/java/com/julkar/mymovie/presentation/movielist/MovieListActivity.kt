package com.julkar.mymovie.presentation.movielist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.julkar.mymovie.MainApplication
import com.julkar.mymovie.R
import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.presentation.movielist.adapter.MovieAdapter
import com.julkar.mymovie.presentation.util.LastItemListener
import com.julkar.mymovie.presentation.util.ListItemListener
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), ListItemListener<Movie> {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieListViewModel

    private val movieAdapter by lazy {
        MovieAdapter(this, object : LastItemListener {
            override fun lastItemClick() {
                movieListLoader.visibility = View.VISIBLE
                viewModel.bindMovieListData(ContentType.MOVIE, ++ContentType.MOVIE.page)
            }
        })
    }

    private val seriesAdapter by lazy{
        MovieAdapter(this, object : LastItemListener {
            override fun lastItemClick() {
                seriesListLoader.visibility = View.VISIBLE
                viewModel.bindMovieListData(ContentType.TV_SERIES, ++ContentType.TV_SERIES.page)
            }
        })
    }

    private val trendingAdapter by lazy{
        MovieAdapter(this, object : LastItemListener {
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

        viewModel.movieState.observe(this) { movieState ->
            when (movieState) {
                is MovieState.Success -> {
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
                    }else if (movieState.type == ContentType.TRENDING) {
                        trendingListLoader.visibility = View.GONE
                        trendingAdapter.submitList(
                            ListMerge(
                                trendingAdapter.currentList,
                                movieState.movieList
                            )
                        )
                    }
                }

                is MovieState.Failure -> {
                    movieListLoader.visibility = View.GONE
                    seriesListLoader.visibility = View.GONE
                    trendingListLoader.visibility = View.GONE

                    Toast.makeText(this, getString(R.string.network_error_msg), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        configureMovieList(movieAdapter, rvMovieList, LinearLayoutManager.HORIZONTAL)
        configureMovieList(seriesAdapter, rvSeriesList, LinearLayoutManager.HORIZONTAL)
        configureMovieList(trendingAdapter, rvTrendingList, LinearLayoutManager.HORIZONTAL)

        viewModel.bindMovieListData(ContentType.MOVIE,ContentType.MOVIE.page)
        movieListLoader.visibility = View.VISIBLE

        viewModel.bindMovieListData(ContentType.TV_SERIES, ContentType.TV_SERIES.page)
        seriesListLoader.visibility = View.VISIBLE

        viewModel.bindMovieListData(ContentType.TRENDING, ContentType.TRENDING.page)
        trendingListLoader.visibility = View.VISIBLE
    }

    private fun configureMovieList(adapter: MovieAdapter<Movie>, recyclerView: RecyclerView, orientation: Int) {
        val layoutManager = LinearLayoutManager(this, orientation, false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onClick(view: View, position: Int, data: Movie) {
        Toast.makeText(this, "id : ${data.id} title: ${data.title}", Toast.LENGTH_SHORT).show()
    }

    private fun <T> ListMerge(first: List<T>, second: List<T>): List<T> {
        val list: MutableList<T> = ArrayList()
        list.addAll(first)
        list.addAll(second)

        return list
    }
}
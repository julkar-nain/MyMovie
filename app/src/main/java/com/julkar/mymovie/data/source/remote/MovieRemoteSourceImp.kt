package com.julkar.mymovie.data.source.remote

import com.julkar.mymovie.di.movie.MovieScope
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.util.API_KEY
import com.julkar.mymovie.util.DEFAULT_API_SORT
import com.julkar.mymovie.util.PRIMARY_MOVIE_RELEASE_YEAR
import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@MovieScope
class MovieRemoteSourceImp @Inject constructor(private val movieApi: MovieApi) : MovieRemoteSource {

    @Throws
    override suspend fun fetchMovieList(page: Int): List<Movie> {
        val response = movieApi.fetchMovieList(API_KEY, PRIMARY_MOVIE_RELEASE_YEAR, DEFAULT_API_SORT, 1)

        return response.movieList
    }
}
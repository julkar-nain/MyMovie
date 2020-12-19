package com.julkar.mymovie.data.repository

import com.julkar.mymovie.data.source.remote.MovieRemoteSource
import com.julkar.mymovie.di.movie.MovieScope
import com.julkar.mymovie.domain.Movie
import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@MovieScope
class MovieRepositoryImp @Inject constructor(private val movieRemoteSource: MovieRemoteSource) :
    MovieRepository {

    override suspend fun getMovieList(page: Int): List<Movie> {
        return movieRemoteSource.fetchMovieList(page)
    }
}
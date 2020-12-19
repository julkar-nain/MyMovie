package com.julkar.mymovie.data.repository

import com.julkar.mymovie.domain.Movie

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
class MovieRepositoryImp : MovieRepository {

    override suspend fun fetchMovieList(): List<Movie> {
        return listOf()
    }
}
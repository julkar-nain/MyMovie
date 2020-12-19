package com.julkar.mymovie.data.source.remote

import com.julkar.mymovie.domain.Movie

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface MovieRemoteSource {

    suspend fun fetchMovieList(page: Int): List<Movie>
}
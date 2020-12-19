package com.julkar.mymovie.data.source.remote

import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface MovieRemoteSource {

    suspend fun fetchMovieList(type: ContentType, page: Int): List<Movie>
}
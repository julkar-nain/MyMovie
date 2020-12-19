package com.julkar.mymovie.data.source.local

import com.julkar.mymovie.domain.MovieDetail

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface MovieLocalSource {
    suspend fun saveMovie(movieEntity: MovieEntity)
    suspend fun getMovieById(id: Int): MovieEntity
    suspend fun updateMovie(movieEntity: MovieEntity)
}
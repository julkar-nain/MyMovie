package com.julkar.mymovie.data.source.local

import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface MovieLocalSource {
    suspend fun saveAllMovies(list: List<Movie>, page: Int, type: Int)
    suspend fun getAllMovies(page: Int, type: Int): List<Movie>
    suspend fun getMovieById(id: Int): MovieDetail
    suspend fun updateMovie(movieDetail: MovieDetail)
}
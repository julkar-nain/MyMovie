package com.julkar.mymovie.presentation.movielist

import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
sealed class MovieListState {
    data class Success(var type: ContentType, var movieList: List<Movie>) : MovieListState()
    data class Failure(var exception: Throwable) : MovieListState()
}
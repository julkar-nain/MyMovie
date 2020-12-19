package com.julkar.mymovie.presentation.movielist

import com.julkar.mymovie.domain.Movie

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
sealed class MovieState {
    data class Success(var movieList: List<Movie>) : MovieState()
    data class Failure(var exception: Throwable) : MovieState()
}
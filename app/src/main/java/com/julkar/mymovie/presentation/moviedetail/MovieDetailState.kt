package com.julkar.mymovie.presentation.moviedetail

import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
sealed class MovieDetailState {
    data class Success(var type: ContentType, var movie: MovieDetail) : MovieDetailState()
    data class Failure(var exception: Throwable) : MovieDetailState()
}

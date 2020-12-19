package com.julkar.mymovie.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.julkar.mymovie.domain.Movie

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
data class MovieListResponse(
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>
)
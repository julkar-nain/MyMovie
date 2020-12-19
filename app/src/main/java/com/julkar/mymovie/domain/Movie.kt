package com.julkar.mymovie.domain

import com.google.gson.annotations.SerializedName

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
data class Movie(
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("poster_path")
    val posterPath: String
)
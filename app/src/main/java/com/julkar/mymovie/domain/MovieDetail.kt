package com.julkar.mymovie.domain

import com.google.gson.annotations.SerializedName

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
data class MovieDetail(
    val id: Int,
    val title: String?,
    val name: String = "",
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    val overview: String,
    val homepage: String,
    val popularity: Double,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val adult: Boolean,
    val video: Boolean,
    @SerializedName("imdb_id")
    val imdbId: String
)

package com.julkar.mymovie.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
data class Movie(
    val id: Int,
    val title: String?,
    val name: String? = "",
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("poster_path")
    val posterPath: String?
): Serializable
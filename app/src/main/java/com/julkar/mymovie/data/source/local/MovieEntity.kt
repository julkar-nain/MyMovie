package com.julkar.mymovie.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String?,
    val name: String? = "",
    val releaseDate: String?,
    val voteCount: Int?,
    val posterPath: String?,
    val page: Int,
    val type: Int,
    val overview: String? = "",
    val homepage: String? = "",
    val popularity: Double? = 0.0,
    val revenue: Long? = 0,
    val runtime: Int? = 0,
    val status: String? = "",
    val tagline: String? = "",
    val adult: Boolean? = false,
    val video: Boolean? = false,
    val imdbId: String? = ""
)

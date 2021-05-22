package com.julkar.mymovie.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val type: Int
)

package com.julkar.mymovie.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val movieId: Int,
    val wishList: Boolean = false
)

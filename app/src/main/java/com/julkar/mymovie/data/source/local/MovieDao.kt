package com.julkar.mymovie.data.source.local

import androidx.room.*

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movieEntity: MovieEntity)

    @Query("SELECT * from movie_table")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("DELETE FROM movie_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Update
    suspend fun update(movieEntity: MovieEntity)
}
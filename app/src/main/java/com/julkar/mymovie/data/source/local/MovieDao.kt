package com.julkar.mymovie.data.source.local

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteConstraintException::class)
    suspend fun insertAll(vararg movieList: MovieEntity)

    @Query("SELECT * from movie_table where page is :page and type is :type")
    suspend fun getMovies(page: Int, type: Int): List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("DELETE FROM movie_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Update
    suspend fun update(movieEntity: MovieEntity)
}
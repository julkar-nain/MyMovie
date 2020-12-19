package com.julkar.mymovie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.julkar.mymovie.data.source.local.MovieDao
import com.julkar.mymovie.data.source.local.MovieEntity
import com.julkar.mymovie.util.DATABASE_VERSION

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Database(entities = [MovieEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
package com.julkar.mymovie.di

import com.julkar.mymovie.data.source.local.MovieDao
import com.julkar.mymovie.data.source.remote.MovieApi
import com.julkar.mymovie.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class MovieModule {

    companion object {

        @Singleton
        @Provides
        fun providesMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }

        @Singleton
        @Provides
        fun providesMovieDao(appDatabase: AppDatabase): MovieDao {
            return appDatabase.movieDao()
        }
    }
}
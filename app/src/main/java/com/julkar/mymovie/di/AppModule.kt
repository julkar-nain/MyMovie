package com.julkar.mymovie.di

import android.app.Application
import androidx.room.Room
import com.julkar.mymovie.data.repository.MovieRepository
import com.julkar.mymovie.data.repository.MovieRepositoryImp
import com.julkar.mymovie.data.source.local.MovieLocalSource
import com.julkar.mymovie.data.source.local.MovieLocalSourceImp
import com.julkar.mymovie.data.source.remote.MovieRemoteSource
import com.julkar.mymovie.data.source.remote.MovieRemoteSourceImp
import com.julkar.mymovie.db.AppDatabase
import com.julkar.mymovie.util.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

    companion object {
        @Singleton
        @Provides
        fun providesRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        fun providesAppDatabase(application: Application): AppDatabase {
            return Room.databaseBuilder<AppDatabase>(
                application,
                AppDatabase::class.java,
                "app-database"
            ).build()
        }
    }

    @Binds
    abstract fun bindsMovieRemoteSource(movieRemoteSourceImp: MovieRemoteSourceImp):
            MovieRemoteSource

    @Binds
    abstract fun bindsMovieLocalDataSource(movieLocalSourceImp: MovieLocalSourceImp):
            MovieLocalSource

    @Binds
    abstract fun bindsMovieRepository(movieRepositoryImp: MovieRepositoryImp):
            MovieRepository
}
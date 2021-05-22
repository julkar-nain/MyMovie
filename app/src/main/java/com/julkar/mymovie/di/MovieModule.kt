package com.julkar.mymovie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.julkar.mymovie.data.repository.MovieRepository
import com.julkar.mymovie.data.repository.MovieRepositoryImp
import com.julkar.mymovie.data.source.local.MovieDao
import com.julkar.mymovie.data.source.local.MovieLocalSource
import com.julkar.mymovie.data.source.local.MovieLocalSourceImp
import com.julkar.mymovie.data.source.remote.MovieApi
import com.julkar.mymovie.data.source.remote.MovieRemoteSource
import com.julkar.mymovie.data.source.remote.MovieRemoteSourceImp
import com.julkar.mymovie.db.AppDatabase
import com.julkar.mymovie.presentation.moviedetail.MovieDetailViewModel
import com.julkar.mymovie.presentation.movielist.MovieListViewModel
import com.julkar.mymovie.presentation.util.ViewModelFactory
import com.julkar.mymovie.presentation.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@InstallIn(ActivityComponent::class)
@Module
abstract class MovieModule {

    companion object {

        @Provides
        fun providesMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }

        @Provides
        fun providesMovieDao(appDatabase: AppDatabase): MovieDao {
            return appDatabase.movieDao()
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

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    @Binds
    abstract fun bindsMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    @Binds
    abstract fun bindsMovieDetailModel(movieDetailViewModel: MovieDetailViewModel): ViewModel
}
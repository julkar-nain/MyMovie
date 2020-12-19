package com.julkar.mymovie.di.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.julkar.mymovie.data.repository.MovieRepository
import com.julkar.mymovie.data.repository.MovieRepositoryImp
import com.julkar.mymovie.data.source.remote.MovieApi
import com.julkar.mymovie.data.source.remote.MovieRemoteSource
import com.julkar.mymovie.data.source.remote.MovieRemoteSourceImp
import com.julkar.mymovie.presentation.moviedetail.MovieDetailViewModel
import com.julkar.mymovie.presentation.movielist.MovieListViewModel
import com.julkar.mymovie.presentation.util.ViewModelFactory
import com.julkar.mymovie.presentation.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Module
abstract class MovieModule {

    companion object {

        @MovieScope
        @Provides
        fun providesMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }
    }

    @Binds
    abstract fun bindsMovieRemoteSource(movieRemoteSourceImp: MovieRemoteSourceImp):
            MovieRemoteSource

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
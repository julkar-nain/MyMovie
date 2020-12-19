package com.julkar.mymovie.di.movie

import com.julkar.mymovie.domain.MovieDetail
import com.julkar.mymovie.presentation.moviedetail.MovieDetailActivity
import com.julkar.mymovie.presentation.movielist.MovieListActivity
import dagger.Subcomponent

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(movieListActivity: MovieListActivity)
    fun inject(movieDetailActivity: MovieDetailActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }
}
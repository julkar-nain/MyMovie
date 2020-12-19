package com.julkar.mymovie.di

import android.app.Application
import com.julkar.mymovie.di.movie.MovieSubComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getMovieSubComponent(): MovieSubComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
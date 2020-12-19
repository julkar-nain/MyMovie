package com.julkar.mymovie

import android.app.Application
import com.julkar.mymovie.di.AppComponent
import com.julkar.mymovie.di.DaggerAppComponent

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
class MainApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}
package com.julkar.mymovie.presentation.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.julkar.mymovie.R
import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.util.EXTRA_CONTENT_TYPE
import com.julkar.mymovie.util.EXTRA_MOVIE

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
        val type = intent.getSerializableExtra(EXTRA_CONTENT_TYPE) as ContentType
    }
}
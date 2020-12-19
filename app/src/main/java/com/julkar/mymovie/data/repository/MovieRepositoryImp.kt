package com.julkar.mymovie.data.repository

import com.julkar.mymovie.data.source.remote.MovieRemoteSource
import com.julkar.mymovie.di.movie.MovieScope
import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail
import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@MovieScope
class MovieRepositoryImp @Inject constructor(private val movieRemoteSource: MovieRemoteSource) :
    MovieRepository {

    override suspend fun getMovieList(type: ContentType, page: Int): List<Movie> {
        return movieRemoteSource.fetchMovieList(type, page)
    }

    override suspend fun getMovieDetail(type: ContentType, id: Int): MovieDetail {
        return movieRemoteSource.fetchMovieDetail(type, id)
    }
}
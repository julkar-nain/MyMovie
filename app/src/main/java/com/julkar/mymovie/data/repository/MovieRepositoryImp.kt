package com.julkar.mymovie.data.repository

import com.julkar.mymovie.data.source.local.MovieEntity
import com.julkar.mymovie.data.source.local.MovieLocalSource
import com.julkar.mymovie.data.source.remote.MovieRemoteSource
import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail
import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
class MovieRepositoryImp @Inject constructor(
    private val movieRemoteSource: MovieRemoteSource,
    private val movieLocalSource: MovieLocalSource
) :
    MovieRepository {

    override suspend fun getMovieList(type: ContentType, page: Int): List<Movie> {
        return movieRemoteSource.fetchMovieList(type, page)
    }

    override suspend fun getMovieDetail(type: ContentType, id: Int): MovieDetail {
        return movieRemoteSource.fetchMovieDetail(type, id)
    }

    override suspend fun getMovieById(id: Int): MovieEntity {
        return movieLocalSource.getMovieById(id)
    }

    override suspend fun saveMovieDetail(movieEntity: MovieEntity) {
        movieLocalSource.saveMovie(movieEntity)
    }

    override suspend fun updateMovieDetail(movieEntity: MovieEntity) {
        movieLocalSource.updateMovie(movieEntity)
    }
}
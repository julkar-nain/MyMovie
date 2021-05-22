package com.julkar.mymovie.data.repository

import com.julkar.mymovie.data.source.local.MovieLocalSource
import com.julkar.mymovie.data.source.remote.MovieRemoteSource
import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Singleton
class MovieRepositoryImp @Inject constructor(
    private val movieRemoteSource: MovieRemoteSource,
    private val movieLocalSource: MovieLocalSource
) :
    MovieRepository {

    override suspend fun getMovieList(type: ContentType, page: Int): List<Movie> {
        return try {
            val movies = movieRemoteSource.fetchMovieList(type, page)

            if (movies.isNullOrEmpty()) {
                movieLocalSource.getAllMovies(page, type.tag)
            } else {
                movieLocalSource.saveAllMovies(movies, page, type.tag)

                movies
            }
        } catch (ex: Exception) {
            movieLocalSource.getAllMovies(page, type.tag)
        }
    }

    override suspend fun getMovieDetail(type: ContentType, id: Int): MovieDetail {
        return movieRemoteSource.fetchMovieDetail(type, id)
    }
}
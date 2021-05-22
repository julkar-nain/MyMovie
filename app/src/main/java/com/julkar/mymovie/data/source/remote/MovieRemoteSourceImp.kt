package com.julkar.mymovie.data.source.remote

import com.julkar.mymovie.domain.ContentType
import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail
import com.julkar.mymovie.util.API_KEY
import com.julkar.mymovie.util.DEFAULT_API_SORT
import com.julkar.mymovie.util.PRIMARY_MOVIE_RELEASE_YEAR
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Singleton
class MovieRemoteSourceImp @Inject constructor(private val movieApi: MovieApi) : MovieRemoteSource {

    @Throws
    override suspend fun fetchMovieList(type: ContentType, page: Int): List<Movie>? {
        val response = movieApi.fetchMovieList(type.url, API_KEY, PRIMARY_MOVIE_RELEASE_YEAR, DEFAULT_API_SORT, page)

        return response.movieList
    }

    override suspend fun fetchMovieDetail(type: ContentType, id: Int): MovieDetail? {
        return movieApi.fetchMovieDetail("${type.urlDetail}/${id}", API_KEY)
    }
}
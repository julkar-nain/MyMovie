package com.julkar.mymovie.data.source.remote

import com.julkar.mymovie.data.source.remote.response.MovieListResponse
import com.julkar.mymovie.domain.Movie
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface MovieApi {
    @GET("3/discover/movie")
    suspend fun fetchMovieList(
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") releaseYear: Int,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int
    ): MovieListResponse
}
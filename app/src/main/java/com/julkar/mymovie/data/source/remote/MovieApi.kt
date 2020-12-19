package com.julkar.mymovie.data.source.remote

import com.julkar.mymovie.data.source.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface MovieApi {
    @GET
    suspend fun fetchMovieList(
        @Url url: String,
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") releaseYear: Int,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int
    ): MovieListResponse
}
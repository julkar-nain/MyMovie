package com.julkar.mymovie.data.source.remote

import com.julkar.mymovie.data.source.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface MovieApi {
    @GET("3/discover/{type}")
    suspend fun fetchMovieList(
        @Path("type") type: String,
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") releaseYear: Int,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int
    ): MovieListResponse
}
package com.julkar.mymovie.data.source.remote

import com.julkar.mymovie.domain.Movie
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
interface MovieApi {
    @GET("3/discover/movie")
    suspend fun fetchMovieList(
        @Header("api_key") apiKey: String,
        @Header("primary_release_year") releaseYear: Int,
        @Header("sort_by") sortBy: String,
        @Header("page") page: Int
    ): List<Movie>
}
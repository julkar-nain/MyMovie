package com.julkar.mymovie.domain

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
enum class ContentType(val url: String, var page: Int = 1) {
    MOVIE("https://api.themoviedb.org/3/discover/movie"),
    TV_SERIES("https://api.themoviedb.org/3/discover/tv"),
    TRENDING("https://api.themoviedb.org/3/trending/all/week")
}
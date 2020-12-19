package com.julkar.mymovie.domain

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
enum class ContentType(val url: String, var page: Int = 1) {
    MOVIE("movie"), TV_SERIES("tv"), TRENDING("trending/all/week")
}
package com.julkar.mymovie.domain

import com.julkar.mymovie.util.*

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
enum class ContentType(
    val url: String,
    val urlDetail: String = "",
    var page: Int = 1,
    val tag: Int = 0
) {
    MOVIE(URL_TYPE_MOVIE, URL_TYPE_MOVIE_DETAIL, tag = 1),
    TV_SERIES(URL_TYPE_TV_SERIES, URL_TYPE_TV_SERIES_DETAIL, tag = 2),
    TRENDING(URL_TYPE_TRENDING, tag = 3)
}
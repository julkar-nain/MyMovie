package com.julkar.mymovie.domain

import com.julkar.mymovie.util.URL_TYPE_MOVIE
import com.julkar.mymovie.util.URL_TYPE_TRENDING
import com.julkar.mymovie.util.URL_TYPE_TV_SERIES

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
enum class ContentType(val url: String, var page: Int = 1) {
    MOVIE(URL_TYPE_MOVIE),
    TV_SERIES(URL_TYPE_TV_SERIES),
    TRENDING(URL_TYPE_TRENDING)
}
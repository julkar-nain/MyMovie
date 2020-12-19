package com.julkar.mymovie.util

/**
 * @author Julkar Nain
 * since 12/19/20.
 */

//network
const val BASE_URL = "https://api.themoviedb.org/"
const val API_KEY = "1a97f3b8d5deee1d649c0025f3acf75c"
const val PRIMARY_MOVIE_RELEASE_YEAR = 2020
const val DEFAULT_API_SORT = "vote_average.desc"
const val IMAGE_URL_POSTER = "https://image.tmdb.org/t/p/w342"
const val IMAGE_URL_BACKDROP = "https://image.tmdb.org/t/p/w780"

const val URL_TYPE_MOVIE = "${BASE_URL}3/discover/movie"
const val URL_TYPE_TV_SERIES = "${BASE_URL}3/discover/tv"
const val URL_TYPE_TRENDING = "${BASE_URL}3/trending/all/week"

const val URL_TYPE_MOVIE_DETAIL = "${BASE_URL}3/movie"
const val URL_TYPE_TV_SERIES_DETAIL = "${BASE_URL}3/tv"

const val EXTRA_MOVIE = "extra_movie"
const val EXTRA_CONTENT_TYPE = "extra_content_type"
package com.julkar.mymovie.data.source.local

import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
class MovieLocalSourceImp @Inject constructor(private val movieDao: MovieDao) : MovieLocalSource {
    override suspend fun saveMovie(movieEntity: MovieEntity) {
        movieDao.insert(movieEntity)
    }

    override suspend fun getMovieById(id: Int): MovieEntity {
        return movieDao.getMovieById(id)
    }

    override suspend fun updateMovie(movieEntity: MovieEntity) {
        movieDao.update(movieEntity)
    }
}
package com.julkar.mymovie.data.source.local

import com.julkar.mymovie.domain.Movie
import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
class MovieLocalSourceImp @Inject constructor(private val movieDao: MovieDao) : MovieLocalSource {

    override suspend fun saveAllMovies(list: List<Movie>, page: Int, type: Int) {
        val args = list.map { getEntityFromMovie(it, page, type) }.toTypedArray()

        movieDao.insertAll(*args)
    }

    override suspend fun getAllMovies(page: Int, type: Int): List<Movie> {
        val entities = movieDao.getMovies(page, type)

        return entities.map { getMovieFromEntity(it) }
    }

    override suspend fun getMovieById(id: Int): Movie {
        val entity = movieDao.getMovieById(id)

        return getMovieFromEntity(entity)
    }

    private fun getMovieFromEntity(entity: MovieEntity) = Movie(
        id = entity.id,
        title = entity.title,
        name = entity.name,
        releaseDate = entity.releaseDate,
        voteCount = entity.voteCount,
        posterPath = entity.posterPath
    )

    private fun getEntityFromMovie(movie: Movie, page: Int, type: Int) = MovieEntity(
        id = movie.id,
        title = movie.title,
        name = movie.name,
        releaseDate = movie.releaseDate,
        voteCount = movie.voteCount,
        posterPath = movie.posterPath,
        page = page,
        type = type
    )
}

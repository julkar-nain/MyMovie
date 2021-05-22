package com.julkar.mymovie.data.source.local

import com.julkar.mymovie.domain.Movie
import com.julkar.mymovie.domain.MovieDetail
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@Singleton
class MovieLocalSourceImp @Inject constructor(private val movieDao: MovieDao) : MovieLocalSource {

    override suspend fun saveAllMovies(list: List<Movie>, page: Int, type: Int) {
        val args = list.map { getEntityFromMovie(it, page, type) }.toTypedArray()

        movieDao.insertAll(*args)
    }

    override suspend fun getAllMovies(page: Int, type: Int): List<Movie> {
        val entities = movieDao.getMovies(page, type)

        return entities.map { getMovieFromEntity(it) }
    }

    override suspend fun getMovieById(id: Int): MovieDetail {
        val entity = movieDao.getMovieById(id)

        return getMovieDetailFromEntity(entity)
    }

    override suspend fun updateMovie(movieDetail: MovieDetail) {
        val entity = movieDao.getMovieById(movieDetail.id)
        movieDao.update(getEntityFromMovieDetail(movieDetail, entity.page, entity.type))
    }

    private fun getEntityFromMovieDetail(movie: MovieDetail, page: Int, type: Int) = MovieEntity(
        id = movie.id,
        title = movie.title,
        name = movie.name,
        releaseDate = movie.releaseDate,
        voteCount = movie.voteCount,
        posterPath = movie.posterPath,
        overview = movie.overview,
        homepage = movie.homepage,
        popularity = movie.popularity,
        revenue = movie.revenue,
        runtime = movie.runtime,
        status = movie.status,
        tagline = movie.tagline,
        adult = movie.adult,
        video = movie.video,
        imdbId = movie.imdbId,
        page = page,
        type = type
    )

    private fun getMovieDetailFromEntity(entity: MovieEntity) = MovieDetail(
        id = entity.id,
        title = entity.title,
        name = entity.name ?: "",
        releaseDate = entity.releaseDate ?: "",
        voteCount = entity.voteCount ?: 0,
        posterPath = entity.posterPath ?: "",
        overview = entity.overview,
        homepage = entity.homepage,
        popularity = entity.popularity,
        revenue = entity.revenue,
        runtime = entity.runtime,
        status = entity.status,
        tagline = entity.tagline,
        adult = entity.adult,
        video = entity.video,
        imdbId = entity.imdbId
    )

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

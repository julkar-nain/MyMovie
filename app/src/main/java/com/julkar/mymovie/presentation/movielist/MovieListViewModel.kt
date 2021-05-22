package com.julkar.mymovie.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julkar.mymovie.data.repository.MovieRepository
import com.julkar.mymovie.domain.ContentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _movieState = MutableLiveData<MovieListState>()

    val movieListState: LiveData<MovieListState> get() = _movieState

    fun bindMovieListData(type: ContentType, page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val list = withContext(Dispatchers.IO) {
                    movieRepository.getMovieList(type, page)
                }.sortedBy { it.releaseDate }
                _movieState.value =
                    MovieListState.Success(
                        type,
                        list
                    )

            } catch (e: Throwable) {
                _movieState.postValue(MovieListState.Failure(e))
            }
        }
    }
}
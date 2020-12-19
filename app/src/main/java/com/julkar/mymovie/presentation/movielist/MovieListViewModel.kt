package com.julkar.mymovie.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julkar.mymovie.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _movieState = MutableLiveData<MovieState>()

    val movieState: LiveData<MovieState> get() = _movieState

    fun bindMovieListData(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movieState.postValue(MovieState.Success(movieRepository.getMovieList(page)))
            } catch (e: Throwable) {
                _movieState.postValue(MovieState.Failure(e))
            }
        }
    }
}
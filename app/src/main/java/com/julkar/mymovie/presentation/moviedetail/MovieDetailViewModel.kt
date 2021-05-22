package com.julkar.mymovie.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.julkar.mymovie.data.repository.MovieRepository
import com.julkar.mymovie.data.source.local.MovieLocalSource
import com.julkar.mymovie.domain.ContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Julkar Nain
 * since 12/19/20.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) :
    ViewModel() {

    private val _movieDetailState = MutableLiveData<MovieDetailState>()

    val movieDetailState: LiveData<MovieDetailState> get() = _movieDetailState

    fun bindMovieDetailData(type: ContentType, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movieDetailState.postValue(
                    MovieDetailState.Success(
                        type,
                        movieRepository.getMovieDetail(type, id)
                    )
                )
            } catch (e: Throwable) {
                _movieDetailState.postValue(MovieDetailState.Failure(e))
            }
        }
    }
}
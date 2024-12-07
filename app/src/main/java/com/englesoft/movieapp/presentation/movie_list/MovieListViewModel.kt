package com.englesoft.movieapp.presentation.movie_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.englesoft.movieapp.domain.usecase.GetMoviesUseCase
import com.englesoft.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    var state by mutableStateOf(MovieListScreenState())
        private set

    init {
        loadMovies(1)
    }

    fun loadMovies(page: Int) {
        viewModelScope.launch {
            state = state.copy(
                currentPage = page,
                isLoading = true,
                error = null,
            )

            val result = getMoviesUseCase("good", null, page)


            state = when (result) {
                is Resource.Success -> state.copy(
                    movies = state.movies?.plus(result.data.orEmpty()),
                    isLoading = false,
                    error = null
                )

                else -> state.copy(
                    movies = null,
                    isLoading = false,
                    error = result.message ?: "An unexpected error occurred."
                )
            }
        }
    }
}
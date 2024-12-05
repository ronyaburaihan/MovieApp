package com.englesoft.movieapp.presentation.home

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
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            val result = getMoviesUseCase("Batman", null)

            state = when (result) {
                is Resource.Success -> state.copy(
                    movies = result.data,
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
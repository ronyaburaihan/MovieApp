package com.englesoft.movieapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.englesoft.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.englesoft.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    var state by mutableStateOf(DetailsScreenState())
        private set

    fun getMovieDetails(imdbID: String) {

        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            val result = getMovieDetailsUseCase(imdbID)

            state = when (result) {
                is Resource.Success -> state.copy(
                    movie = result.data,
                    isLoading = false,
                    error = null
                )

                else -> state.copy(
                    movie = null,
                    isLoading = false,
                    error = result.message ?: "An unexpected error occurred."
                )
            }
        }
    }
}
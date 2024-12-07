package com.englesoft.movieapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.englesoft.movieapp.domain.model.Movie
import com.englesoft.movieapp.domain.usecase.GetMoviesUseCase
import com.englesoft.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        loadMovies()
    }

    private fun loadMovies() {
        // Set loading state
        setLoadingState()

        // Fetch carousels and rails in parallel
        viewModelScope.launch {
            try {
                val latestMovies = async { getMoviesUseCase("Latest", null) }
                val batmanMovies = async { getMoviesUseCase("Batman", null) }
                val latestMovies2022 = async { getMoviesUseCase("Latest", "2022") }

                // Await the results and ensure the carousel is limited to 5 items
                val latestMoviesResult = latestMovies.await()
                val batmanMoviesResult = batmanMovies.await()
                val latestMovies2022Result = latestMovies2022.await()

                // Check for errors in the response
                if (latestMoviesResult is Resource.Error || batmanMoviesResult is Resource.Error || latestMovies2022Result is Resource.Error) {
                    handleError(latestMoviesResult)
                    return@launch
                }

                // Update state with the results (limiting carousels to 5 items)
                state = state.copy(
                    movieCarousels = latestMoviesResult.data?.take(5),  // Limit to 5
                    batmanRails = batmanMoviesResult.data,
                    latestRails = latestMovies2022Result.data,
                    isLoading = false
                )
            } catch (e: Exception) {
                // Handle any unexpected errors
                handleError(Resource.Error(message = "An unexpected error occurred: ${e.localizedMessage}"))
            }
        }
    }

    // Set the loading state
    private fun setLoadingState() {
        state = state.copy(
            isLoading = true,
            error = null
        )
    }

    // Handle errors
    private fun handleError(result: Resource<List<Movie>>) {
        state = state.copy(
            movieCarousels = null,
            latestRails = null,
            batmanRails = null,
            isLoading = false,
            error = result.message ?: "An unexpected error occurred."
        )
    }
}
package com.englesoft.movieapp.di

import com.englesoft.movieapp.domain.repository.MovieRepository
import com.englesoft.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.englesoft.movieapp.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(repository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(repository: MovieRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }
}

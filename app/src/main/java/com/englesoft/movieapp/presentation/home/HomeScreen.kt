package com.englesoft.movieapp.presentation.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.englesoft.movieapp.domain.model.Movie
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                // Show loading spinner
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error != null -> {
                // Show error message
                Text(
                    text = state.error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.movieCarousels != null -> {
                // Show content
                HomeScreenContent(state = state)
            }
        }
    }
}

@Composable
fun HomeScreenContent(state: HomeScreenState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                rememberScrollState(),
                orientation = Orientation.Vertical
            )
    ) {
        // Carousel with movie banners
        MovieBannerCarouselWithIndicators(movies = state.movieCarousels)

        Spacer(modifier = Modifier.height(16.dp)) // Spacing between sections

        // Rail 1: Batman Movies
        Text(
            text = "Batman Movies",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        MovieRail(movies = state.batmanRails)

        Spacer(modifier = Modifier.height(16.dp))

        // Rail 2: Latest Movies 2022
        Text(
            text = "Latest Movies (2022)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        MovieRail(movies = state.latestRails)
    }
}

@Composable
fun MovieBannerCarouselWithIndicators(movies: List<Movie>?) {
    val pagerState = rememberPagerState(pageCount = { movies?.size ?: 0 })
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) { currentPage ->
            Card(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(movies?.get(currentPage)?.poster),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = ""
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        PageIndicator(
            pageCount = movies?.size ?: 0,
            currentPage = pagerState.currentPage,
            modifier = Modifier
        )
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorDots(isSelected = it == currentPage, modifier = modifier)
        }
    }
}

@Composable
fun IndicatorDots(isSelected: Boolean, modifier: Modifier) {
    val size = animateDpAsState(targetValue = if (isSelected) 12.dp else 10.dp, label = "")
    Box(
        modifier = modifier
            .padding(2.dp)
            .size(size.value)
            .clip(CircleShape)
            .background(
                if (isSelected) Color(0xff373737) else Color(0xA8373737)
            )
    )
}

@Composable
fun MovieRail(movies: List<Movie>?) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        movies?.let {
            items(it) { movie ->
                MovieCard(movie)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(250.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(movie.poster),
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
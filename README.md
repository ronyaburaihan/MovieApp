### Movie Streaming App

This movie streaming app displays movie listings and allows users to view movie details and play video content. The app integrates OMDB API to fetch movie details and provides a video player for content playback. It follows a clean architecture pattern, using MVVM architecture with Dependency Injection (DI) for modularity.

---

### Features:
- **Carousel** with 5 movie banners
- **Batman Movies** Rail (Filtered using OMDB API with title "Batman")
- **Latest Movies 2022** Rail (Filtered using OMDB API with year 2022)
- **Movie Listing Page** with pagination and filtering options
- **Movie Content Details Page** with information fetched from OMDB API
- **Video Player** that plays the movie content, with functionality to stop and resume the video at the previously played position

---

### Architecture

The app follows **Clean Architecture** principles, dividing the project into layers with clear separation of concerns. We use the **MVVM (Model-View-ViewModel)** pattern to manage the state and logic, making the app testable and scalable. 

#### Key Components:
1. **Domain Layer**: 
   - Contains use cases that define the app's business logic.
2. **Data Layer**: 
   - Manages data from different sources like API and local storage.
   - Uses repositories to abstract the data-fetching mechanism.
3. **Presentation Layer**: 
   - Displays data to the user using Composables.
   - Contains the UI logic and ViewModel to manage the UI state.

#### Dependency Injection (DI):
- We use **Hilt** for dependency injection, which simplifies the management of dependencies and makes the app more modular and easier to test.

---

### Prerequisites

- **Android Studio** with **Jetpack Compose** enabled
- **JDK 11 or later** installed
- **OMDB API Key** (Free) for fetching movie data

---

### Setup Instructions

#### 1. Clone the repository

Clone the repository using Git:

```bash
https://github.com/ronyaburaihan/MovieApp
```

#### 2. Configure OMDB API Key

To use the OMDB API, you'll need an API key. 

- Visit [OMDB API](https://www.omdbapi.com/apikey.aspx) to get your API key.
- Add your API key to the project:

  Add below lines at the end of your `local.properties` file. Then run the project.
```properties

api_key=your own api key
```

#### 3. Build and Run the App

Once you have everything set up, you can build and run the app in **Android Studio**:

1. Select an emulator or connect a physical Android device.
2. Press **Run** (green play button) to launch the app.

---

### App Structure

#### Home Screen

The home screen displays:
1. A **Carousel** with 5 movie banners that auto-plays every 2 seconds.
2. **Rail 1**: A list of "Batman" movies fetched from OMDB using a title filter.
3. **Rail 2**: A list of "Latest Movies 2022" fetched from OMDB using a year filter.

#### Movie Listing Page

A paginated movie listing page where users can scroll and load more movies by calling the OMDB API with different parameters.

#### Movie Details Page

Displays detailed information about the selected movie, including:
- **Title**
- **Plot**
- **Year, Genre, other details from OMDB**

It also includes a **video player** that can play streaming content. Users can stop and resume the video, and it will continue from the last played position.

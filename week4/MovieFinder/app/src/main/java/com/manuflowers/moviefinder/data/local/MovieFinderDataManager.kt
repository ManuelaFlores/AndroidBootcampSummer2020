package com.manuflowers.moviefinder.data.local

import androidx.lifecycle.LiveData
import com.manuflowers.moviefinder.data.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieFinderDataManager {

    val isUserLoggedIn: Boolean

    fun saveUserState(userState: Boolean)

    fun getAllMovies(): Flow<MutableList<MovieModel>>

    fun getMoviesByCategory(category: String): Flow<MutableList<MovieModel>>

    suspend fun insertMovie(movieModel: MovieModel)

    suspend fun insertAllMovies(movies: List<MovieModel>)

}
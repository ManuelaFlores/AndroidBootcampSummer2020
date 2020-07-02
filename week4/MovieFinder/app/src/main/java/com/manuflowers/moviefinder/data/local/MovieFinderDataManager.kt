package com.manuflowers.moviefinder.data.local

import androidx.lifecycle.LiveData
import com.manuflowers.moviefinder.data.models.MovieModel

interface MovieFinderDataManager {

    val isUserLoggedIn: Boolean

    fun saveUserState(userState: Boolean)

    fun getAllMovies(): LiveData<MutableList<MovieModel>>

    fun getMoviesByCategory(category: String): LiveData<MutableList<MovieModel>>

    suspend fun insertMovie(movieModel: MovieModel)

    suspend fun insertAllMovies(movies: List<MovieModel>)

}
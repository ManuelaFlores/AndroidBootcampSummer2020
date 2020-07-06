package com.manuflowers.moviefinder.data.local

import com.manuflowers.moviefinder.data.local.database.MovieDao
import com.manuflowers.moviefinder.data.local.preferences.MovieFinderPreferences
import com.manuflowers.moviefinder.data.models.MovieModel
import kotlinx.coroutines.flow.Flow

class MovieFinderDataManagerImpl(private val movieDao: MovieDao) : MovieFinderDataManager {
    private val preferences = MovieFinderPreferences()

    override fun saveUserState(userState: Boolean) {
        preferences.saveUserState(userState)
    }

    override val isUserLoggedIn: Boolean
        get() = preferences.userState

    override fun getAllMovies(): Flow<MutableList<MovieModel>> {
        return movieDao.getAllMovies()
    }

    override fun getMoviesByCategory(category: String): Flow<MutableList<MovieModel>> {
        return movieDao.getMoviesByCategory(category)
    }

    override suspend fun insertMovie(movieModel: MovieModel) {
        movieDao.insert(movieModel)
    }

    override suspend fun insertAllMovies(movies: List<MovieModel>) {
        movieDao.insertAllMovies(movies)
    }
}
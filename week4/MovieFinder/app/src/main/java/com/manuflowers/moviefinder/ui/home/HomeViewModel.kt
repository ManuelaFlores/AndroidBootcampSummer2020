package com.manuflowers.moviefinder.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.manuflowers.moviefinder.data.local.MovieFinderDataManager
import com.manuflowers.moviefinder.data.local.MovieFinderDataManagerImpl
import com.manuflowers.moviefinder.data.local.database.MovieDao
import com.manuflowers.moviefinder.data.local.database.MovieFinderDatabase

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieFinderDataManager

    /**
     * Initialize and instance of movie Dao and repository
     * */
    init {
        val movieDao: MovieDao =
            MovieFinderDatabase.getDataBase(application, viewModelScope).movieDao()
        repository = MovieFinderDataManagerImpl(movieDao)
    }

    fun getMovies() = repository.getAllMovies().asLiveData()

    fun getMoviesByCategory(category: String) = repository.getMoviesByCategory(category).asLiveData()

}
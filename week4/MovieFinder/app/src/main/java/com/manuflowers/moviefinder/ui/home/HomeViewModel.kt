package com.manuflowers.moviefinder.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.manuflowers.moviefinder.data.local.MovieFinderDataManager
import com.manuflowers.moviefinder.data.local.MovieFinderDataManagerImpl
import com.manuflowers.moviefinder.data.local.database.MovieDao
import com.manuflowers.moviefinder.data.local.database.MovieFinderDatabase
import com.manuflowers.moviefinder.data.models.MovieModel

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

    fun getMovies(): LiveData<MutableList<MovieModel>> {
        return repository.getAllMovies()
    }

    fun getMoviesByCategory(category: String): LiveData<MutableList<MovieModel>> {
        return repository.getMoviesByCategory(category)
    }
}
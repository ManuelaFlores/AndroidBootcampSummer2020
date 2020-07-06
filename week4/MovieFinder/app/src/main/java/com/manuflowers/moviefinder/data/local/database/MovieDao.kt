package com.manuflowers.moviefinder.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuflowers.moviefinder.data.models.MovieModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * from movie_table")
    fun getAllMovies(): Flow<MutableList<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieModel: MovieModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMovies(moviesList: List<MovieModel>)

    @Query("SELECT * from movie_table WHERE category = :category")
    fun getMoviesByCategory(category: String): Flow<MutableList<MovieModel>>

}
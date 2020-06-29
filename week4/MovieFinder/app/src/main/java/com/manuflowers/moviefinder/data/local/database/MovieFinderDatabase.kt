package com.manuflowers.moviefinder.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.manuflowers.moviefinder.data.models.MovieModel
import com.manuflowers.moviefinder.data.local.moviesList
import kotlinx.coroutines.*

@Database(entities = [MovieModel::class], version = 1)
abstract class MovieFinderDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: MovieFinderDatabase? = null

        fun getDataBase(context: Context, coroutineScope: CoroutineScope): MovieFinderDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieFinderDatabase::class.java,
                    "movie_finder_database"
                ).addCallback(MovieFinderDatabaseCallback(coroutineScope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun movieDao(): MovieDao

    private class MovieFinderDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { movieFinderDatabase ->
                scope.launch {
                    val movieDao = movieFinderDatabase.movieDao()
                    populateDataBase(movieDao)
                }
            }
        }

        private suspend fun populateDataBase(movieDao: MovieDao) {
            movieDao.insertAllMovies(
                moviesList
            )
        }
    }
}
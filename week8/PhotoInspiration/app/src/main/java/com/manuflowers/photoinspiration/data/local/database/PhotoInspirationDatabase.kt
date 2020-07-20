package com.manuflowers.photoinspiration.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manuflowers.photoinspiration.data.models.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class PhotoInspirationDatabase: RoomDatabase() {

    abstract fun photosDao(): PhotosDao

    companion object {
        @Volatile
        private var INSTANCE: PhotoInspirationDatabase? = null

        fun getDataBase(context: Context): PhotoInspirationDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhotoInspirationDatabase::class.java,
                    "photo_inspiration_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
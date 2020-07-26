package com.manuflowers.photoinspiration.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manuflowers.photoinspiration.data.models.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class PhotoInspirationDatabase: RoomDatabase() {

    abstract fun photosDao(): PhotosDao
}
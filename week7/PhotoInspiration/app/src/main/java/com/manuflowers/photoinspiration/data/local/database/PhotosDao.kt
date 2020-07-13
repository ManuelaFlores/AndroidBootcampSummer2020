package com.manuflowers.photoinspiration.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuflowers.photoinspiration.data.models.PhotoEntity

@Dao
interface PhotosDao {

    @Query("SELECT * from photo_table")
    fun getAllPhotos(): LiveData<MutableList<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPhotos(photosList: List<PhotoEntity>)

}
package com.manuflowers.photoinspiration.data.local.database

import androidx.room.*
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Query("SELECT * from photo_table")
    fun getAllPhotos(): Flow<MutableList<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPhotos(photosList: List<PhotoEntity>)

    @Query("DELETE FROM photo_table")
    suspend fun deleteAllPhotos()

}
package com.manuflowers.photoinspiration.ui.home.viewstate

import com.manuflowers.photoinspiration.data.models.PhotoEntity

sealed class HomeState
class PhotosSuccess(val data: MutableList<PhotoEntity>) : HomeState()
class PhotosOffLine(val data: MutableList<PhotoEntity>) : HomeState()
class PhotosFailure(val error: String) : HomeState()
package com.manuflowers.photoinspiration.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.data.models.PhotoResponse
import com.manuflowers.photoinspiration.util.inflate

class PhotosAdapter : RecyclerView.Adapter<PhotosViewHolder>() {

    private var photosList = mutableListOf<PhotoResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = parent.inflate(R.layout.photo_view_holder)
        return PhotosViewHolder(view)
    }

    override fun getItemCount(): Int = photosList.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    fun addData(photosList: MutableList<PhotoResponse>) {
        this.photosList.clear()
        this.photosList.addAll(photosList)
        notifyDataSetChanged()
    }
}
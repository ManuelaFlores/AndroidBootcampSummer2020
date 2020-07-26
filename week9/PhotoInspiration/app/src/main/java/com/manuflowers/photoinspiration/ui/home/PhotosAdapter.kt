package com.manuflowers.photoinspiration.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.util.inflate

class PhotosAdapter : RecyclerView.Adapter<PhotosViewHolder>() {

    private var photosList = mutableListOf<PhotoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layout = parent.inflate(R.layout.photo_view_holder)
        return PhotosViewHolder(layout)
    }

    override fun getItemCount(): Int = photosList.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    fun addData(photosList: MutableList<PhotoEntity>) {
        this.photosList.clear()
        this.photosList.addAll(photosList)
        notifyDataSetChanged()
    }

}

package com.manuflowers.photoinspiration.ui.home

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.util.loadUrl
import com.manuflowers.photoinspiration.util.loadUrlAsCircle
import kotlinx.android.synthetic.main.photo_view_holder.view.*

class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

    private lateinit var photoEntity: PhotoEntity

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(photoEntity: PhotoEntity) {
        this.photoEntity = photoEntity

        itemView.movieImageView.loadUrl(photoEntity.smallUrl)
        itemView.userNameTextView.text= photoEntity.userName
        itemView.userImageView.loadUrlAsCircle(photoEntity.userProfileImage)
    }

    override fun onClick(view: View) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(photoEntity)
        view.findNavController().navigate(action)
    }
}
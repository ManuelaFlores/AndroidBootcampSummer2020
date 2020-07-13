package com.manuflowers.photoinspiration.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.photoinspiration.data.models.PhotoResponse
import com.manuflowers.photoinspiration.util.loadUrl
import com.manuflowers.photoinspiration.util.loadUrlAsCircle
import kotlinx.android.synthetic.main.photo_view_holder.view.*

class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

    private lateinit var photoResponse: PhotoResponse

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(photoResponse: PhotoResponse) {
        this.photoResponse = photoResponse

        photoResponse.urls.small.apply {

        }

        itemView.movieImageView.loadUrl(photoResponse.urls.small)
        itemView.userNameTextView.text= photoResponse.user.name
        itemView.userImageView.loadUrlAsCircle(photoResponse.user.profile_image.medium)
    }

    override fun onClick(view: View) {

    }
}
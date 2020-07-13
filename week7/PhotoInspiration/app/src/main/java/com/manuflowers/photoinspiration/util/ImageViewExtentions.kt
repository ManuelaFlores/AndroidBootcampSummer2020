package com.manuflowers.photoinspiration.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.manuflowers.photoinspiration.R

fun ImageView.loadUrl(url: String, @DrawableRes drawablePlaceholder: Int = R.drawable.placeholder) =
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .centerCrop()
        .placeholder(drawablePlaceholder)
        .into(this)
        .clearOnDetach()

fun ImageView.loadUrlAsCircle(url: String, @DrawableRes drawablePlaceholder: Int = R.drawable.placeholder) =
    Glide.with(this)
        .load(url)
        .placeholder(drawablePlaceholder)
        .error(drawablePlaceholder)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .transform(CircleCrop())
        .into(this)
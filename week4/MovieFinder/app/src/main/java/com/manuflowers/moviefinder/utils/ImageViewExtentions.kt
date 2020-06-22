package com.manuflowers.moviefinder.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.manuflowers.moviefinder.R

fun ImageView.loadUrl(url: String, @DrawableRes drawablePlaceholder: Int = R.drawable.placeholder) =
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(drawablePlaceholder)
        .into(this)
        .clearOnDetach()
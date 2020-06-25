package com.manuflowers.moviefinder.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutResource: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutResource, this, attachToRoot)
}
package com.manuflowers.moviefinder.ui.home

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.moviefinder.data.models.MovieModel
import com.manuflowers.moviefinder.utils.loadUrl
import kotlinx.android.synthetic.main.movie_view_holder.view.*

class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

    private lateinit var movie: MovieModel

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(movie: MovieModel) {
        this.movie = movie
        movie.poster?.let{
            itemView.movieImageView.loadUrl(it)
        }
        itemView.movieTitleTextView.text = movie.title
    }

    override fun onClick(view: View) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie)
        view.findNavController().navigate(action)
    }
}
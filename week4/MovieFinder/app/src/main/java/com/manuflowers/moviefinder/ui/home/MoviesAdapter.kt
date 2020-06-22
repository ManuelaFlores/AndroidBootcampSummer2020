package com.manuflowers.moviefinder.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.moviefinder.R
import com.manuflowers.moviefinder.data.models.MovieModel
import com.manuflowers.moviefinder.utils.inflate

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    private val moviesList = mutableListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = parent.inflate(R.layout.movie_view_holder)
        return MoviesViewHolder(view)
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    /**
     *Populates the list of the class
     * @param moviesList list of movies
     * */
    fun addData(moviesList: MutableList<MovieModel>) {
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }
}
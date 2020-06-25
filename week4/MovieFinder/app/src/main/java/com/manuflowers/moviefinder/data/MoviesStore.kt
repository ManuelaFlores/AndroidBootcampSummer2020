package com.manuflowers.moviefinder.data

object MoviesStore {
    private val movies = moviesList

    fun getAllMovies() = movies

    fun getFamilyMovies() = moviesList.filter { it.category == "Family" }.toMutableList()

    fun getFantasyMovies() = movies.filter { it.category == "Fantasy" }.toMutableList()

    fun getSuperHeroesMovies() = movies.filter { it.category == "SuperHeroes" }.toMutableList()

    fun getActionMovies() = movies.filter { it.category == "Action" }.toMutableList()
}
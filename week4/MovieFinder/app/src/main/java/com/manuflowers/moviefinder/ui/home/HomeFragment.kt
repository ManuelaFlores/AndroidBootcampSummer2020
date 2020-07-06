package com.manuflowers.moviefinder.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.manuflowers.moviefinder.R
import com.manuflowers.moviefinder.data.models.MovieModel
import com.manuflowers.moviefinder.utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory() }

    private val moviesAdapter by lazy {
        MoviesAdapter()
    }

    private var currentList = mutableListOf<MovieModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            it.mainBottomNavigationView.visibility = View.VISIBLE
        }

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spacing = resources.getDimensionPixelSize(R.dimen.movie_card_layout_margin)
        setupRecyclerview(spacing = spacing)
        homeRecyclerView.adapter = moviesAdapter
        if (currentList.isEmpty()) {
            observeAllMovies()
        } else {
            moviesAdapter.addData(currentList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionMovies -> {
                observeMoviesByCategory(getString(R.string.action_category))
            }
            R.id.familyMovies -> {
                observeMoviesByCategory(getString(R.string.family_category))
            }
            R.id.fantasyMovies -> {
                observeMoviesByCategory(getString(R.string.fantasy_category))
            }
            R.id.superHeroesMovies -> {
                observeMoviesByCategory(getString(R.string.superheroes_category))
            }
            R.id.actionAllMovies -> {
                observeAllMovies()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Get Data from viewModel and observe changes
     * @param category, the current category for search in database
     * */
    private fun observeMoviesByCategory(category: String) {
        homeViewModel.getMoviesByCategory(category).observe(viewLifecycleOwner) { movies ->
            moviesAdapter.addData(movies)
            currentList = movies
        }
    }

    private fun observeAllMovies() {
        homeViewModel.getMovies().observe(viewLifecycleOwner) { movies ->
            moviesAdapter.addData(movies)
            currentList = movies
        }
    }

    /**
     * Set item decoration to have equal spacing
     * @param spanCount the number of columns
     * @param spacing the space between columns and rows in pixels
     * */
    private fun setupRecyclerview(spanCount: Int = 2, spacing: Int) {
        homeRecyclerView.apply {
            this.addItemDecoration(SpacingItemDecoration(spanCount, spacing))
            this.layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}
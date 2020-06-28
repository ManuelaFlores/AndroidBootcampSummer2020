package com.manuflowers.moviefinder.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.manuflowers.moviefinder.R
import com.manuflowers.moviefinder.data.MoviesStore
import com.manuflowers.moviefinder.utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val moviesAdapter by lazy {
        MoviesAdapter()
    }

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
        moviesAdapter.addData(MoviesStore.getAllMovies().toMutableList())
        updateSpanCount( spacing)
        homeRecyclerView.adapter = moviesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionMovies -> {
                moviesAdapter.addData(MoviesStore.getActionMovies())
            }
            R.id.familyMovies -> {
                moviesAdapter.addData(MoviesStore.getFamilyMovies())
            }
            R.id.fantasyMovies ->{
                moviesAdapter.addData( MoviesStore.getFantasyMovies())
            }
            R.id.superHeroesMovies ->{
                moviesAdapter.addData(MoviesStore.getSuperHeroesMovies())
            }
            R.id.actionAllMovies -> {
                moviesAdapter.addData(MoviesStore.getAllMovies())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Setup the recyclerview depending of the orientation screen
     * @param spacing the space between rows and columns
     * */
    private fun updateSpanCount(spacing: Int) {
        if (Configuration.ORIENTATION_LANDSCAPE == resources.configuration.orientation) {
            setupRecyclerview(3, spacing)
        } else {
            setupRecyclerview(2, spacing)
        }
    }

    /**
     * Set item decoration to have equal spacing
     * @param spanCount the number of columns
     * @param spacing the space between columns and rows in pixels
     * */
    private fun setupRecyclerview(spanCount: Int, spacing: Int) {
        homeRecyclerView.apply {
            this.addItemDecoration(SpacingItemDecoration(spanCount, spacing))
            this.layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}
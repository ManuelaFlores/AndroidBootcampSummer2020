package com.manuflowers.moviefinder.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.manuflowers.moviefinder.R
import com.manuflowers.moviefinder.data.MoviesStore
import com.manuflowers.moviefinder.utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val moviesAdapter by lazy {
        MoviesAdapter()
    }

    private var isInLandScape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            isInLandScape = it.getBoolean(IS_IN_LAND_SCAPE_KEY, false)
        }

        val spacing = resources.getDimensionPixelSize(R.dimen.movie_card_layout_margin)
        moviesAdapter.addData(MoviesStore.getAllMovies().toMutableList())
        if (isInLandScape) {
            setupRecyclerview(3, spacing)
        } else {
            setupRecyclerview(2, spacing)
        }
        homeRecyclerView.adapter = moviesAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_IN_LAND_SCAPE_KEY, !isInLandScape)
    }

    private fun setupRecyclerview(spanCount: Int, spacing: Int) {
        homeRecyclerView.apply {
            this.addItemDecoration(SpacingItemDecoration(spanCount, spacing))
            this.layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    companion object {
        fun newInstance() = HomeFragment()

        const val IS_IN_LAND_SCAPE_KEY = "IS_IN_LAND_SCAPE_KEY"
    }
}
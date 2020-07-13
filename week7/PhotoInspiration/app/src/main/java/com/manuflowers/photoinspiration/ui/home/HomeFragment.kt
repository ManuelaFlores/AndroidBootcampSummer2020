package com.manuflowers.photoinspiration.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.util.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory() }

    private val photosAdapter by lazy {
        PhotosAdapter()
    }

    private var currentList = mutableListOf<PhotoEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        val spacing = resources.getDimensionPixelSize(R.dimen.movie_card_layout_margin)
        homeRecyclerView.adapter = photosAdapter
        setupRecyclerview(spacing = spacing)
        homeViewModel.getMovies(::observeAllMovies)
    }

    private fun observeAllMovies() {
        homeViewModel.getAllMoviesFromDataBase().observe(viewLifecycleOwner, Observer {
            photosAdapter.addData(it.toMutableList())
            currentList = it.toMutableList()
            hideProgressBar()
        })

        homeViewModel.errorNetworkMessageLiveData.observe(viewLifecycleOwner, Observer {
            showToast(getString(it.message))
        })
    }

    private fun showProgressBar() {
        homeProgressBar.visibility = View.VISIBLE
        homeRecyclerView.visibility = View.GONE
    }

    private fun hideProgressBar() {
        homeProgressBar.visibility = View.GONE
        homeRecyclerView.visibility = View.VISIBLE
    }


    /**
     * Set item decoration to have equal spacing
     * @param spanCount the number of columns
     * @param spacing the space between columns and rows in pixels
     * */
    private fun setupRecyclerview(spanCount: Int = 2, spacing: Int) {

        val layoutManager =
            GridLayoutManager(activity, spanCount, GridLayoutManager.VERTICAL, false)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((position + 1) % 3 == 0) spanCount else 1
            }
        }

        homeRecyclerView.apply {
            this.addItemDecoration(SpacingItemDecoration(spanCount, spacing))
            this.layoutManager = layoutManager
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}
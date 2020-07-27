package com.manuflowers.photoinspiration.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.data.remote.networking.NetworkStatusChecker
import com.manuflowers.photoinspiration.ui.home.list.PhotosAdapter
import com.manuflowers.photoinspiration.ui.home.viewstate.PhotosFailure
import com.manuflowers.photoinspiration.ui.home.viewstate.PhotosOffLine
import com.manuflowers.photoinspiration.ui.home.viewstate.PhotosSuccess
import com.manuflowers.photoinspiration.util.SpacingItemDecoration
import com.manuflowers.photoinspiration.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(PhotoInspirationApplication.repository)
    }

    private val photosAdapter by lazy {
        PhotosAdapter()
    }

    private val hasInternetConnection by lazy {
        NetworkStatusChecker(PhotoInspirationApplication.getAppContext()).hasInternetConnection()
    }

    private var currentList = mutableListOf<PhotoEntity>()

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
        showProgressBar()
        homeRecyclerView.adapter = photosAdapter
        val spacing = resources.getDimensionPixelSize(R.dimen.movie_card_layout_margin)
        setUpListeners()
        if (currentList.isEmpty()) {
            observeAllMovies()
            if (hasInternetConnection) {
                homeViewModel.getPhotos()
            } else {
                homeViewModel.getPhotosFromDb()
            }
        } else {
            photosAdapter.addData(currentList)
            hideProgressBar()
        }
        setupRecyclerview(spacing = spacing)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sortAlphabetically -> {
                observeMoviesByCategory()
            }
            R.id.getAllPhotos -> {
                observeAllMovies()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeAllMovies() {
        homeViewModel.homeStateLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is PhotosSuccess -> {
                    hideProgressBar()
                    photosAdapter.addData(it.data)
                    currentList = it.data
                    homeSwipeRefreshLayout.isRefreshing = false
                }
                is PhotosOffLine -> {
                    hideProgressBar()
                    photosAdapter.addData(it.data)
                    currentList = it.data
                    homeSwipeRefreshLayout.isRefreshing = false
                    activity?.toast("There's no internet connection, you're in offline mode :)")
                }
                is PhotosFailure -> {
                    activity?.toast(it.error)
                }
            }
        })
    }

    private fun observeMoviesByCategory() {
        homeViewModel.getAllPhotosToOrder().observe(viewLifecycleOwner, Observer { allPhotos ->
            allPhotos.sortBy { it.userName }
            photosAdapter.addData(allPhotos)
            currentList = allPhotos
        })
    }

    /**
     * Show and hide progress bar
     **/
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

    /**
     * Setup swipe refresh layout to update photos from server
     * */
    private fun setUpListeners() {
        homeSwipeRefreshLayout.setOnRefreshListener {
            //homeViewModel.getMovies(::observeAllMovies)
        }
    }
}
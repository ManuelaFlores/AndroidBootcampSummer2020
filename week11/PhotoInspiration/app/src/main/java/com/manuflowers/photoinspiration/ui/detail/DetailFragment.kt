package com.manuflowers.photoinspiration.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.ui.main.MainActivity
import com.manuflowers.photoinspiration.util.loadUrl
import com.manuflowers.photoinspiration.util.loadUrlAsCircle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_detail_nested_scrolling.*
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val photo by lazy {
        arguments?.let {
            DetailFragmentArgs.fromBundle(it).photo
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            it.mainBottomNavigationView.visibility = View.GONE
        }
        sharedElementEnterTransition =
            TransitionInflater.from(this.context).inflateTransition(R.transition.change_bounds)
        sharedElementReturnTransition =
            TransitionInflater.from(this.context).inflateTransition(R.transition.change_bounds)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
        setupListeners()
        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.title = photo?.userName ?: getString(R.string.app_name)
        postponeEnterTransition()
        startPostponedTransition()
    }

    private fun startPostponedTransition() {
        Glide.with(this)
            .load(photo?.regularUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

            })
            .into(movieImageView)
    }

    private fun bindData() {
        photo?.let {
            movieImageView.loadUrl(it.regularUrl)
            userImageView.loadUrlAsCircle(it.userProfileImage)
            userNameTextView.text = it.userName
            userBioTextView.text = it.userBio ?: getString(R.string.not_available)
            userLocationTextView.text = it.userLocation ?: getString(R.string.not_available)
        }
    }

    private fun setupListeners() {
        seeProfileButton.setOnClickListener {
            photo?.let {
                val action = DetailFragmentDirections.actionDetailFragmentToWebViewFragment(
                    it.userProfileWeb ?: ""
                )
                this.findNavController().navigate(action)
            }
        }
    }

}
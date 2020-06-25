package com.manuflowers.moviefinder.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manuflowers.moviefinder.R
import com.manuflowers.moviefinder.utils.loadUrl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val movie by lazy {
        arguments?.let {
            DetailFragmentArgs.fromBundle(it).movie
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    private fun bindData() {
        movie?.let{
            movieImageView.loadUrl(it.poster?: "")
            releasedDateTextView.text = it.releaseDate
            descriptionTextView.text = it.summary
            titleTextView.text = it.title
        }
    }
}
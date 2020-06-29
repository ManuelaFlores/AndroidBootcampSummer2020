package com.manuflowers.moviefinder.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.manuflowers.moviefinder.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        logoutButton.setOnClickListener {
            profileViewModel.saveUserState(false)
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }
}
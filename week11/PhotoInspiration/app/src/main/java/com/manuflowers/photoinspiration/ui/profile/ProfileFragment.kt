package com.manuflowers.photoinspiration.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
            (activity as MainActivity).closeSynchronization()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }
}
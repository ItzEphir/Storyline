package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.common.log.log
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentAuthBinding
import com.ephirium.storyline.presentation.MainViewModel
import com.ephirium.storyline.ui.MainActivity

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val binding: FragmentAuthBinding by viewBinding()

    private val mainViewModel: MainViewModel by lazy {
        (requireActivity() as MainActivity).viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.currentUser.observe(viewLifecycleOwner) {
            findNavController().navigate(
                when (it) {
                    null -> R.id.action_authFragment_to_signInFragment
                    else -> R.id.action_authFragment_to_tabFragment2
                }
            )
        }

        log("Auth")

        if (savedInstanceState == null) {
            mainViewModel.observeCurrentUser(getString(R.string.saved_user_key))
        }
    }
}
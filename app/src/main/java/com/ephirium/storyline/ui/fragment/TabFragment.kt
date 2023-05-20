package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentTabBinding
import com.ephirium.storyline.presentation.PostViewModel

class TabFragment : Fragment(R.layout.fragment_tab) {

    private val binding: FragmentTabBinding by viewBinding()

    private val navHost: NavHostFragment by lazy { childFragmentManager.findFragmentById(R.id.tab_fragment_container) as NavHostFragment }

    private val navController by lazy { navHost.navController }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[PostViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            viewModel.observePosts()
            when(menuItem.itemId){
                R.id.posts ->{
                    when(val destination = navController.findDestination(R.id.post_page_graph)){
                        null -> navController.navigate(R.id.post_page_graph)
                        else -> navController.navigate(destination.id)
                    }
                }
                R.id.create ->{
                    when(val destination = navController.findDestination(R.id.create_story_graph)){
                        null -> navController.navigate(R.id.create_story_graph)
                        else -> navController.navigate(destination.id)
                    }
                }
                R.id.profile ->{
                    when(val destination = navController.findDestination(R.id.profile_graph)){
                        null -> navController.navigate(R.id.profile_graph)
                        else -> navController.navigate(destination.id)
                    }
                }
            }
            true
        }
    }

}
package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentCreatePostBinding

class CreatePostFragment: Fragment(R.layout.fragment_create_post) {
    val binding: FragmentCreatePostBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
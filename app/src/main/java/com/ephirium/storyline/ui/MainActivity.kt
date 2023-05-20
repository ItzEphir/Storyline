package com.ephirium.storyline.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.ActivityMainBinding
import com.ephirium.storyline.presentation.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    val viewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModel.Factory(
                application,
                getSharedPreferences(
                    getString(R.string.shared_preferences_key),
                    Context.MODE_PRIVATE
                )
            )
        )[MainViewModel::class.java]
    }
}
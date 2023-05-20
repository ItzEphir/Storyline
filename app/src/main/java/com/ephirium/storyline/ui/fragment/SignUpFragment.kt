package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.common.log.log
import com.ephirium.common.log.logError
import com.ephirium.data.repository.SignUpRepository
import com.ephirium.data.storage.UserDto
import com.ephirium.domain.usecase.SignUpUseCase
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentSignUpBinding
import com.ephirium.storyline.ui.MainActivity

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding: FragmentSignUpBinding by viewBinding()

    private val mainViewModel by lazy {
        (requireActivity() as MainActivity).viewModel
    }

    private val navController by lazy { findNavController() }

    private val signUpUseCase = SignUpUseCase(SignUpRepository())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val isEmpty =
                binding.layoutAuth.login.text == null ||
                        binding.layoutAuth.password.text == null ||
                        binding.email.text == null ||
                        binding.displayName.text == null ||
                        binding.layoutAuth.login.text?.isEmpty() == true ||
                        binding.layoutAuth.password.text?.isEmpty() == true ||
                        binding.email.text?.isEmpty() == true ||
                        binding.displayName.text?.isEmpty() == true

            if (!isEmpty) {
                signUpUseCase.signUp(
                    binding.layoutAuth.login.text!!.toString(),
                    binding.email.text!!.toString(),
                    binding.layoutAuth.password.text!!.toString(),
                    binding.displayName.text!!.toString(),
                    object : DataConstListener<UserDto?> {
                        override fun onChange(value: UserDto?) {
                            when (value) {
                                null -> {
                                    log("user is null")
                                    Toast.makeText(
                                        requireContext(),
                                        "CurrentUser is null",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    log("user is not null")
                                    mainViewModel.saveNewCurrentUser(
                                        getString(R.string.saved_user_key),
                                        value
                                    )
                                    log("trying to navigate")
                                    navController.navigate(R.id.action_signInFragment_to_tabFragment2)
                                }
                            }
                        }
                    },
                    object : ErrorListener {
                        override fun onError(exception: Exception) {
                            logError(exception.toString())
                            Toast.makeText(
                                requireContext(),
                                "User cannot be signed up",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }

        }

        binding.signin.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
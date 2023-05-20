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
import com.ephirium.data.repository.SignInRepository
import com.ephirium.data.storage.UserDto
import com.ephirium.domain.usecase.SignInUseCase
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentSignInBinding
import com.ephirium.storyline.ui.MainActivity

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()

    private val mainViewModel by lazy {
        (requireActivity() as MainActivity).viewModel
    }

    private val signInUseCase = SignInUseCase(SignInRepository())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        log("SignIn")

        binding.loginButton.setOnClickListener {
            val isEmpty =
                binding.layoutAuth.login.text == null || binding.layoutAuth.password.text == null ||
                        binding.layoutAuth.login.text?.isEmpty() == true || binding.layoutAuth.password.text?.isEmpty() == true
            if (!isEmpty) {
                log(binding.layoutAuth.login.text.toString())
                signInUseCase.signIn(
                    binding.layoutAuth.login.text.toString(),
                    binding.layoutAuth.password.text.toString(),
                    object : DataConstListener<UserDto?> {
                        override fun onChange(value: UserDto?) {
                            if (value != null) {
                                mainViewModel.saveNewCurrentUser(
                                    getString(R.string.saved_user_key),
                                    value
                                )
                                findNavController().navigate(R.id.action_signInFragment_to_tabFragment2)
                            }
                        }
                    },
                    object : ErrorListener {
                        override fun onError(exception: Exception) {
                            Toast.makeText(
                                requireContext(),
                                "User already exists",
                                Toast.LENGTH_SHORT
                            ).show()
                            logError(exception.toString())
                        }

                    })
            }
        }

        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

}
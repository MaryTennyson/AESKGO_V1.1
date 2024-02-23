package com.ebraratabay.aeskgo.views.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.ebraratabay.aeskgo.R
import com.ebraratabay.aeskgo.databinding.FragmentLoginPageBinding
import com.ebraratabay.aeskgo.enums.AuthResults.Failure
import com.ebraratabay.aeskgo.enums.AuthResults.Loading
import com.ebraratabay.aeskgo.enums.AuthResults.Success
import com.ebraratabay.aeskgo.models.FirebaseAuthUser
import com.ebraratabay.aeskgo.services.SharedPreferencesService
import com.ebraratabay.aeskgo.viewmodels.LoginPageViewModel
import com.ebraratabay.aeskgo.views.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginPageFragment : Fragment() {

    companion object {
        fun newInstance() = LoginPageFragment()
    }

    private lateinit var viewModel: LoginPageViewModel
    private var _binding: FragmentLoginPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.signupButton.setOnClickListener {
            signUpButtonClicked()
        }

        binding.signinButton.setOnClickListener {
            signInButtonClicked()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginPageViewModel::class.java)
        isCurrentUserExist()
    }

    fun isCurrentUserExist() {
        val currentuser = viewModel.isUserExist()
        println(currentuser)
        if (currentuser) {

            var intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun getUserFromEditText(): FirebaseAuthUser {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return FirebaseAuthUser(email, password)
    }

    fun signInButtonClicked() {
        val user = getUserFromEditText()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signInUser(user)
                viewModel.authState.collect {
                    when (it) {
                        is Success -> {
                            editUserID(it.value.toString())
                            println("success")
                            val action = R.id.action_loginPageFragment_to_signInPageFragment
                            Navigation.findNavController(binding.root).navigate(action)
                        }

                        is Failure -> {
                            println("Failure")

                        }

                        is Loading -> {
                            println("Loading")


                        }
                    }
                }
            }
        }
    }

    fun signUpButtonClicked() {
        val user = getUserFromEditText()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signUpUser(user)
                viewModel.authState.collect {
                    when (it) {
                        is Success -> {
                            editUserID(it.value.toString())
                            println("signInButtonClicked ${it.value}")
                            val action = R.id.action_loginPageFragment_to_signInPageFragment
                            Navigation.findNavController(binding.root).navigate(action)

                        }

                        is Failure -> {
                            println("Failerue")

                        }

                        is Loading -> {
                            println("Loading")

                        }
                    }
                }
            }
        }

    }


    fun editUserID(userID: String) {
        SharedPreferencesService("user_ID", requireContext()).editStringFromSP(userID)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
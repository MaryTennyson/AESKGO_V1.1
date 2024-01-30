package com.ebraratabay.aeskgo.views.fragments.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.ebraratabay.aeskgo.R
import com.ebraratabay.aeskgo.databinding.FragmentLoginPageBinding
import com.ebraratabay.aeskgo.enums.AuthEnumClass
import com.ebraratabay.aeskgo.enums.AuthResults
import com.ebraratabay.aeskgo.services.FirebaseAuthService
import com.ebraratabay.aeskgo.viewmodels.LoginPageViewModel
import com.ebraratabay.aeskgo.views.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.sign

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

        binding.signInButton.setOnClickListener {
            val email= binding.emailEditText.text.toString()
            val password= binding.passwordEditText.text.toString()
            viewModel.signupButton(it,email,password)
        }
        return view
    }


    fun signUpButtonClicked( ){
        val email= binding.emailEditText.text.toString()
        val password= binding.passwordEditText.text.toString()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.authState.collect{
                    when(it){
                        is AuthResults.Success -> {
                            val action= R.id.action_loginPageFragment_to_signInPageFragment
                            Navigation.findNavController(binding.root).navigate(action)

                        }
                        is AuthResults.Failure ->{

                        }
                    }
                }
            }
        }
        var intent= Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
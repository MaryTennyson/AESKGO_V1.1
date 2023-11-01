package com.ebraratabay.aeskgo.views.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ebraratabay.aeskgo.R
import com.ebraratabay.aeskgo.viewmodels.LoginPageViewModel

class LoginPageFragment : Fragment() {

    companion object {
        fun newInstance() = LoginPageFragment()
    }

    private lateinit var viewModel: LoginPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
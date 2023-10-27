package com.ebraratabay.aeskgo.views.fragments.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ebraratabay.aeskgo.R

class SignInPageFragment : Fragment() {

    companion object {
        fun newInstance() = SignInPageFragment()
    }

    private lateinit var viewModel: SignInPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
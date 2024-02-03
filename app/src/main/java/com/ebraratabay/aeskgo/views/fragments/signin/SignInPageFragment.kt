package com.ebraratabay.aeskgo.views.fragments.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ebraratabay.aeskgo.databinding.FragmentSignInPageBinding
import com.ebraratabay.aeskgo.viewmodels.SignInPageViewModel
import com.ebraratabay.aeskgo.views.activities.MainActivity

class SignInPageFragment : Fragment() {

    companion object {
        fun newInstance() = SignInPageFragment()
    }

    private lateinit var viewModel: SignInPageViewModel
    private var _binding: FragmentSignInPageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInPageBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.button2.setOnClickListener {

            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.ebraratabay.aeskgo.views.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebraratabay.aeskgo.adapters.VehicleListAdapter
import com.ebraratabay.aeskgo.databinding.FragmentMainPageBinding
import com.ebraratabay.aeskgo.enums.AuthResults
import com.ebraratabay.aeskgo.models.Vehicle
import com.ebraratabay.aeskgo.viewmodels.MainPageViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainPageFragment : Fragment() {

    private lateinit var viewModel: MainPageViewModel
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private val vehicleAdapter = VehicleListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)
        binding.vehicleRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.vehicleRecyclerView.adapter = vehicleAdapter
        viewModel.getVehicleData()
        getVehicleData()
    }



    fun getVehicleData() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.vehicleState.collect {
                    when (it) {
                        is AuthResults.Success -> {
                            vehicleAdapter.updateVehicleList(it.value as ArrayList<Vehicle>)
                        }

                        is AuthResults.Failure -> {

                        }

                        is AuthResults.Loading -> {

                        }
                    }
                }
            }
        }
    }

}
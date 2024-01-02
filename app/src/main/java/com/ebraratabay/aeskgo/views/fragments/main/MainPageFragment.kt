package com.ebraratabay.aeskgo.views.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebraratabay.aeskgo.R
import com.ebraratabay.aeskgo.adapters.VehicleListAdapter
import com.ebraratabay.aeskgo.databinding.ActivityMainBinding
import com.ebraratabay.aeskgo.databinding.FragmentLoginPageBinding
import com.ebraratabay.aeskgo.databinding.FragmentMainPageBinding
import com.ebraratabay.aeskgo.models.Vehicle
import com.ebraratabay.aeskgo.viewmodels.LoginPageViewModel


class MainPageFragment : Fragment() {


    private lateinit var viewModel: FragmentMainPageBinding
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private val  vehicleAdapter= VehicleListAdapter(arrayListOf())

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
        binding.vehicleRecyclerView.layoutManager=LinearLayoutManager(activity)
        binding.vehicleRecyclerView.adapter= vehicleAdapter
       val vehicle1= Vehicle("Arabam1", "Var bir açıklması", R.drawable.eletra)
        val vehicle2= Vehicle("Arabam2", "Var bir açıklması", R.drawable.eletra)
        var vehicleList= ArrayList<Vehicle>()
        vehicleList.add(vehicle1)
        vehicleList.add(vehicle2)
        vehicleAdapter.updateVehicleList(vehicleList)
    }


}
package com.ebraratabay.aeskgo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebraratabay.aeskgo.databinding.VehicleCardBinding

class VehicleListAdapter(val vehicleList: ArrayList<String>): RecyclerView.Adapter<VehicleListAdapter.VehicleHolder>() {
    class VehicleHolder(val binding: VehicleCardBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleHolder {
val binding= VehicleCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VehicleHolder(binding)
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

    override fun onBindViewHolder(holder: VehicleHolder, position: Int) {

    }
}
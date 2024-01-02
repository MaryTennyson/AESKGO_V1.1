package com.ebraratabay.aeskgo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebraratabay.aeskgo.databinding.VehicleCardBinding
import com.ebraratabay.aeskgo.models.Vehicle

class VehicleListAdapter(val vehicleList: ArrayList<Vehicle>): RecyclerView.Adapter<VehicleListAdapter.VehicleHolder>() {
    class VehicleHolder(val binding: VehicleCardBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleHolder {
val binding= VehicleCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VehicleHolder(binding)
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

    override fun onBindViewHolder(holder: VehicleHolder, position: Int) {
       holder.binding.titleTextView.text= vehicleList.get(position).title
        holder.binding.descriptionTextView.text=vehicleList.get(position).description
        holder.binding.continueTextView.text= "Devam ediniz"
        holder.binding.imageCardView.setImageResource(vehicleList.get(position).imageID)

    }
    fun updateVehicleList(newAdvertList: List<Vehicle>) {
        vehicleList.clear()
        vehicleList.addAll(newAdvertList)
        notifyDataSetChanged()
    }
}
package com.ebraratabay.aeskgo.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ebraratabay.aeskgo.databinding.VehicleCardBinding
import com.ebraratabay.aeskgo.models.Vehicle
import com.ebraratabay.aeskgo.views.activities.MapsActivity

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

        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context,MapsActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }
    fun updateVehicleList(newAdvertList: List<Vehicle>) {
        vehicleList.clear()
        vehicleList.addAll(newAdvertList)
        notifyDataSetChanged()
    }
}
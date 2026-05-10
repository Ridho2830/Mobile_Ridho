package psti.unram.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import psti.unram.myapplication.R
import psti.unram.myapplication.model.Vehicle

class VehicleAdapter(
    private val onEditClick: (Vehicle) -> Unit  // Lambda untuk handle klik Edit
) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    private val vehicles = mutableListOf<Vehicle>()

    fun setData(newVehicles: List<Vehicle>) {
        vehicles.clear()
        vehicles.addAll(newVehicles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicles[position])
    }

    override fun getItemCount(): Int = vehicles.size

    inner class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvModel: TextView = itemView.findViewById(R.id.tvModel)
        private val tvDetail: TextView = itemView.findViewById(R.id.tvDetail)
        private val btnEdit: Button = itemView.findViewById(R.id.btnEdit)

        fun bind(vehicle: Vehicle) {
            tvModel.text = vehicle.model
            tvDetail.text = "${vehicle.type} - ${vehicle.manufacturer}"

            // Handle klik tombol Edit
            btnEdit.setOnClickListener {
                onEditClick(vehicle)
            }
        }
    }
}
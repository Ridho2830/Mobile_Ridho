package psti.unram.tugasstorage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PendudukAdapter(private val list: List<PendudukEntity>) : RecyclerView.Adapter<PendudukAdapter.PendudukViewHolder>() {

    class PendudukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tv_nama)
        val tvNik: TextView = itemView.findViewById(R.id.tv_nik)
        val tvAlamat: TextView = itemView.findViewById(R.id.tv_alamat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendudukViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_penduduk, parent, false)
        return PendudukViewHolder(view)
    }

    override fun onBindViewHolder(holder: PendudukViewHolder, position: Int) {
        val item = list[position]
        holder.tvNama.text = item.nama
        holder.tvNik.text = "NIK: ${item.nik}"
        holder.tvAlamat.text = "Alamat: ${item.alamat}"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

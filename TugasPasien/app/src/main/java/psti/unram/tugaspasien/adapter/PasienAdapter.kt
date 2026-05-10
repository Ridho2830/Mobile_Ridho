package psti.unram.tugaspasien.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import psti.unram.tugaspasien.R
import psti.unram.tugaspasien.model.Pasien

class PasienAdapter(
    private val onEditClick: (Pasien) -> Unit,
    private val onDeleteClick: (Pasien) -> Unit
) : RecyclerView.Adapter<PasienAdapter.PasienViewHolder>() {

    private var pasiens = listOf<Pasien>()

    fun setData(data: List<Pasien>) {
        this.pasiens = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pasien, parent, false)
        return PasienViewHolder(view)
    }

    override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {
        val pasien = pasiens[position]
        holder.bind(pasien, onEditClick, onDeleteClick)
    }

    override fun getItemCount(): Int = pasiens.size

    class PasienViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        private val tvTglLahir: TextView = itemView.findViewById(R.id.tvTglLahir)
        private val tvJenisKelamin: TextView = itemView.findViewById(R.id.tvJenisKelamin)
        private val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
        private val tvNoTelp: TextView = itemView.findViewById(R.id.tvNoTelp)
        private val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bind(pasien: Pasien, onEditClick: (Pasien) -> Unit, onDeleteClick: (Pasien) -> Unit) {
            tvNama.text = "Nama: ${pasien.nama ?: "-"}"
            tvTglLahir.text = "Tgl Lahir: ${pasien.tanggalLahir ?: "-"}"
            tvJenisKelamin.text = "Jenis Kelamin: ${pasien.jenisKelamin ?: "-"}"
            tvAlamat.text = "Alamat: ${pasien.alamat ?: "-"}"
            tvNoTelp.text = "No Telp: ${pasien.noTelepon ?: "-"}"

            btnEdit.setOnClickListener { onEditClick(pasien) }
            btnDelete.setOnClickListener { onDeleteClick(pasien) }
        }
    }
}

package psti.unram.tugasstorage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MahasiswaAdapter(
    private var list: MutableList<Mahasiswa>,
    private val onHapus: (Mahasiswa) -> Unit
) : RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAvatar: TextView = view.findViewById(R.id.tvAvatar)
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvNim: TextView = view.findViewById(R.id.tvNim)
        val tvJurusan: TextView = view.findViewById(R.id.tvJurusan)
        val tvIpk: TextView = view.findViewById(R.id.tvIpk)
        val btnHapus: ImageButton = view.findViewById(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mahasiswa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val m = list[position]
        val ctx = holder.itemView.context
        holder.tvAvatar.text = m.nama.firstOrNull()?.uppercase() ?: "?"

        holder.tvNama.text = m.nama
        holder.tvNim.text = ctx.getString(R.string.prefix_nim, m.nim)
        holder.tvJurusan.text = m.jurusan
        holder.tvIpk.text = "%.2f".format(m.ipk)

        val ipkColor = when {
            m.ipk >= 3.5 -> R.color.ipk_high
            m.ipk >= 2.75 -> R.color.ipk_mid
            else -> R.color.ipk_low
        }
        holder.tvIpk.background.setTint(
            ContextCompat.getColor(ctx, ipkColor)
        )

        holder.btnHapus.setOnClickListener { onHapus(m) }
    }

    override fun getItemCount() = list.size

    fun updateData(newList: List<Mahasiswa>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
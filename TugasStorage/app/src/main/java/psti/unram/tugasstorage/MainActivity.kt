package psti.unram.tugasstorage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var storage: StorageMahasiswaInterface
    private lateinit var adapter: MahasiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storage = psti.unram.tugasstorage.room.StorageRoom(this)

        adapter = MahasiswaAdapter(mutableListOf()) { mahasiswa ->
            lifecycleScope.launch {
                storage.delete(mahasiswa.id)
                loadData()
            }
        }

        findViewById<RecyclerView>(R.id.rvMahasiswa).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        findViewById<FloatingActionButton>(R.id.fabTambah).setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = storage.getAll()
            adapter.updateData(data)

            val tvKosong = findViewById<TextView>(R.id.tvKosong)
            val rvMahasiswa = findViewById<RecyclerView>(R.id.rvMahasiswa)
            if (data.isEmpty()) {
                tvKosong.visibility = View.VISIBLE
                rvMahasiswa.visibility = View.GONE
            } else {
                tvKosong.visibility = View.GONE
                rvMahasiswa.visibility = View.VISIBLE
            }
        }
    }
}

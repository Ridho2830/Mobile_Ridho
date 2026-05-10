package psti.unram.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import psti.unram.myapplication.adapter.VehicleAdapter
import psti.unram.myapplication.network.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var rvVehicles: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var adapter: VehicleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi view
        rvVehicles = findViewById(R.id.rvVehicles)
        progressBar = findViewById(R.id.progressBar)
        fabAdd = findViewById(R.id.fabAdd)

        // Setup RecyclerView
        adapter = VehicleAdapter(
            onEditClick = { vehicle ->
                val intent = Intent(this, EditVehicleActivity::class.java).apply {
                    putExtra("VEHICLE_ID", vehicle.id)
                    putExtra("VEHICLE_MODEL", vehicle.model)
                    putExtra("VEHICLE_TYPE", vehicle.type)
                    putExtra("VEHICLE_MANUFACTURER", vehicle.manufacturer)
                }
                startActivity(intent)
            }
        ) // <-- Perbaikan: Menambahkan kurung tutup yang hilang
        
        rvVehicles.layoutManager = LinearLayoutManager(this)
        rvVehicles.adapter = adapter

        // Setup FAB
        fabAdd.setOnClickListener {
            val intent = Intent(this, AddVehicleActivity::class.java)
            startActivity(intent)
        }

        // Load data dari API
        loadVehicles()
    }

    override fun onResume() {
        super.onResume()
        loadVehicles() // Refresh data saat kembali ke MainActivity
    }

    private fun loadVehicles() {
        lifecycleScope.launch {
            showLoading(true)

            try {
                val response = RetrofitClient.apiService.getVehicles()

                if (response.isSuccessful) {
                    val vehicles = response.body()?.data ?: emptyList()
                    adapter.setData(vehicles)

                    if (vehicles.isEmpty()) {
                        showMessage("Belum ada data vehicle")
                    }
                } else {
                    showMessage("Gagal mengambil data: ${response.code()}")
                }
            } catch (e: Exception) {
                showMessage("Error: ${e.message}")
            } finally {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

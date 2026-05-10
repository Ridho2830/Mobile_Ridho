package psti.unram.tugaspasien

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import psti.unram.tugaspasien.adapter.PasienAdapter
import psti.unram.tugaspasien.network.RetrofitClient
import psti.unram.tugaspasien.network.TokenManager

class MainActivity : AppCompatActivity() {

    private lateinit var rvPasiens: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvUserName: TextView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var adapter: PasienAdapter
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tokenManager = TokenManager(this)

        rvPasiens = findViewById(R.id.rvPasiens)
        progressBar = findViewById(R.id.progressBar)
        tvUserName = findViewById(R.id.tvUserName)
        fabAdd = findViewById(R.id.fabAdd)

        val userName = tokenManager.getUserName() ?: "User"
        tvUserName.text = "Selamat Datang, $userName"

        adapter = PasienAdapter(
            onEditClick = { pasien ->
                val intent = Intent(this, FormPasienActivity::class.java).apply {
                    putExtra("PASIEN_ID", pasien.id)
                    putExtra("PASIEN_NAMA", pasien.nama)
                    putExtra("PASIEN_TGL_LAHIR", pasien.tanggalLahir)
                    putExtra("PASIEN_JK", pasien.jenisKelamin)
                    putExtra("PASIEN_ALAMAT", pasien.alamat)
                    putExtra("PASIEN_NOTELP", pasien.noTelepon)
                }
                startActivity(intent)
            },
            onDeleteClick = { pasien ->
                showDeleteDialog(pasien.id)
            }
        )
        
        rvPasiens.layoutManager = LinearLayoutManager(this)
        rvPasiens.adapter = adapter

        fabAdd.setOnClickListener {
            val intent = Intent(this, FormPasienActivity::class.java)
            startActivity(intent)
        }

        loadPasiens()
    }

    override fun onResume() {
        super.onResume()
        loadPasiens()
    }

    private fun loadPasiens() {
        val token = tokenManager.getToken()
        if (token == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        lifecycleScope.launch {
            showLoading(true)

            try {
                val bearerToken = "Bearer $token"
                val response = RetrofitClient.apiService.getPasien(bearerToken)

                if (response.isSuccessful && response.body()?.success == true) {
                    val pasiens = response.body()?.data ?: emptyList()
                    adapter.setData(pasiens)

                    if (pasiens.isEmpty()) {
                        // Tidak perlu showMessage di sini jika memang datanya kosong, cukup listnya kosong.
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

    private fun showDeleteDialog(id: Int?) {
        if (id == null) return
        AlertDialog.Builder(this)
            .setTitle("Hapus Pasien")
            .setMessage("Apakah Anda yakin ingin menghapus data pasien ini?")
            .setPositiveButton("Ya") { _, _ -> deletePasien(id) }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun deletePasien(id: Int) {
        val token = tokenManager.getToken() ?: return
        val bearerToken = "Bearer $token"

        lifecycleScope.launch {
            showLoading(true)
            try {
                val response = RetrofitClient.apiService.deletePasien(bearerToken, id)
                if (response.isSuccessful && response.body()?.success == true) {
                    showMessage("Berhasil menghapus pasien")
                    loadPasiens() // Refresh list
                } else {
                    val msg = response.body()?.message ?: "Gagal menghapus"
                    showMessage(msg)
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

package psti.unram.tugaspasien

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import psti.unram.tugaspasien.model.Pasien
import psti.unram.tugaspasien.network.RetrofitClient
import psti.unram.tugaspasien.network.TokenManager

class FormPasienActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etTglLahir: EditText
    private lateinit var etJenisKelamin: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etNoTelp: EditText
    private lateinit var btnSimpan: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvTitle: TextView
    private lateinit var tokenManager: TokenManager

    private var pasienId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pasien)

        tokenManager = TokenManager(this)

        etNama = findViewById(R.id.etNama)
        etTglLahir = findViewById(R.id.etTglLahir)
        etJenisKelamin = findViewById(R.id.etJenisKelamin)
        etAlamat = findViewById(R.id.etAlamat)
        etNoTelp = findViewById(R.id.etNoTelp)
        btnSimpan = findViewById(R.id.btnSimpan)
        progressBar = findViewById(R.id.progressBar)
        tvTitle = findViewById(R.id.tvTitle)

        // Cek jika ini adalah mode edit
        if (intent.hasExtra("PASIEN_ID")) {
            pasienId = intent.getIntExtra("PASIEN_ID", -1)
            tvTitle.text = "Edit Pasien"
            etNama.setText(intent.getStringExtra("PASIEN_NAMA"))
            etTglLahir.setText(intent.getStringExtra("PASIEN_TGL_LAHIR"))
            etJenisKelamin.setText(intent.getStringExtra("PASIEN_JK"))
            etAlamat.setText(intent.getStringExtra("PASIEN_ALAMAT"))
            etNoTelp.setText(intent.getStringExtra("PASIEN_NOTELP"))
        } else {
            tvTitle.text = "Tambah Pasien"
        }

        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val tglLahir = etTglLahir.text.toString().trim()
            val jk = etJenisKelamin.text.toString().trim()
            val alamat = etAlamat.text.toString().trim()
            val noTelp = etNoTelp.text.toString().trim()

            if (nama.isEmpty() || tglLahir.isEmpty() || jk.isEmpty() || alamat.isEmpty() || noTelp.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pasien = Pasien(
                id = pasienId,
                nama = nama,
                tanggalLahir = tglLahir,
                jenisKelamin = jk,
                alamat = alamat,
                noTelepon = noTelp
            )

            simpanPasien(pasien)
        }
    }

    private fun simpanPasien(pasien: Pasien) {
        val token = tokenManager.getToken()
        if (token == null) {
            Toast.makeText(this, "Sesi Anda telah habis. Silakan login kembali.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val bearerToken = "Bearer $token"

        lifecycleScope.launch {
            showLoading(true)
            try {
                val response = if (pasienId != null && pasienId != -1) {
                    RetrofitClient.apiService.updatePasien(bearerToken, pasienId!!, pasien)
                } else {
                    RetrofitClient.apiService.createPasien(bearerToken, pasien)
                }

                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(this@FormPasienActivity, "Berhasil menyimpan pasien", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val errorMessage = response.body()?.message ?: "Gagal menyimpan pasien"
                    Toast.makeText(this@FormPasienActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@FormPasienActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        btnSimpan.isEnabled = !isLoading
    }
}

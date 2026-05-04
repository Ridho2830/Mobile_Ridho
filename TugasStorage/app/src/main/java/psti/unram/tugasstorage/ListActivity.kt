package psti.unram.tugasstorage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {

    private lateinit var storage: StorageMahasiswaInterface
    private lateinit var prefs: StoragePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_mahasiswa)

        storage = psti.unram.tugasstorage.room.StorageRoom(this)
        prefs = StoragePreferences(this)

        val etNim     = findViewById<EditText>(R.id.etNim)
        val etNama    = findViewById<EditText>(R.id.etNama)
        val etJurusan = findViewById<EditText>(R.id.etJurusan)
        val etIpk     = findViewById<EditText>(R.id.etIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        etJurusan.setText(prefs.jurusanTerakhir)

        btnSimpan.setOnClickListener {
            val nim     = etNim.text.toString().trim()
            val nama    = etNama.text.toString().trim()
            val jurusan = etJurusan.text.toString().trim()
            val ipkStr  = etIpk.text.toString().trim()

            if (nim.isEmpty() || nama.isEmpty() || jurusan.isEmpty() || ipkStr.isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_field_kosong), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ipk = ipkStr.toDoubleOrNull()
            if (ipk == null || ipk < 0.0 || ipk > 4.0) {
                Toast.makeText(this, getString(R.string.msg_ipk_invalid), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                storage.insert(Mahasiswa(nim = nim, nama = nama, jurusan = jurusan, ipk = ipk))
                prefs.jurusanTerakhir = jurusan
                Toast.makeText(
                    this@ListActivity,
                    getString(R.string.msg_data_tersimpan),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}

package psti.unram.tugasstorage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var etNik: TextInputEditText
    private lateinit var etNama: TextInputEditText
    private lateinit var etAlamat: TextInputEditText
    private lateinit var btnSimpan: Button
    private lateinit var btnLihatData: Button

    private lateinit var db: PendudukDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNik = findViewById(R.id.et_nik)
        etNama = findViewById(R.id.et_nama)
        etAlamat = findViewById(R.id.et_alamat)
        btnSimpan = findViewById(R.id.btn_simpan)
        btnLihatData = findViewById(R.id.btn_lihat_data)

        db = Room.databaseBuilder(
            applicationContext,
            PendudukDatabase::class.java, "penduduk_db"
        ).allowMainThreadQueries().build()

        btnSimpan.setOnClickListener {
            val nik = etNik.text.toString().trim()
            val nama = etNama.text.toString().trim()
            val alamat = etAlamat.text.toString().trim()

            if (nik.isEmpty() || nama.isEmpty() || alamat.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val penduduk = PendudukEntity(nik = nik, nama = nama, alamat = alamat)
            db.pendudukDao().insert(penduduk)
            
            Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
            etNik.text?.clear()
            etNama.text?.clear()
            etAlamat.text?.clear()
        }

        btnLihatData.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}

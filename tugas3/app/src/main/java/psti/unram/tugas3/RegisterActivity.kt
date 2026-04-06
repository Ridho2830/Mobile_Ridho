package psti.unram.tugas3

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)

        val etNama = findViewById<TextInputEditText>(R.id.etNama)
        val etNim = findViewById<TextInputEditText>(R.id.etNim)
        val spinnerProdi = findViewById<Spinner>(R.id.spinnerProdi)
        val rbLaki = findViewById<RadioButton>(R.id.rbLaki)
        val rbPerempuan = findViewById<RadioButton>(R.id.rbPerempuan)
        val cbGameing = findViewById<CheckBox>(R.id.cbGaming)
        val cbMusik = findViewById<CheckBox>(R.id.cbMusik)
        val cbOlahraga = findViewById<CheckBox>(R.id.cbOlahraga)
        val cbMembaca = findViewById<CheckBox>(R.id.cbMembaca)
        val cbKoding = findViewById<CheckBox>(R.id.cbKoding)
        val btnTampilkan = findViewById<Button>(R.id.btnTampilkan)

        // Setup Spinner
        val prodiList = listOf(
            "-- Pilih Program Studi --",
            "Teknik Informatika",
            "Sistem Informasi",
            "Ilmu Komputer",
            "Teknik Elektro",
            "Manajemen Informatika"
        )
        val adapter = ArrayAdapter(this, R.layout.spinner_item, prodiList)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerProdi.adapter = adapter

        btnTampilkan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val nim = etNim.text.toString().trim()
            val prodiIndex = spinnerProdi.selectedItemPosition
            val prodiSelected = spinnerProdi.selectedItem.toString()

            // Validasi
            if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
                etNama.requestFocus()
                return@setOnClickListener
            }
            if (nim.isEmpty()) {
                etNim.error = "NIM tidak boleh kosong"
                etNim.requestFocus()
                return@setOnClickListener
            }
            if (prodiIndex == 0) {
                Snackbar.make(it, "Pilih Program Studi terlebih dahulu", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!rbLaki.isChecked && !rbPerempuan.isChecked) {
                Snackbar.make(it, "Pilih Jenis Kelamin terlebih dahulu", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val jenisKelamin = if (rbLaki.isChecked) "Laki-laki" else "Perempuan"

            val hobis = mutableListOf<String>()
            if (cbGameing.isChecked) hobis.add("Gaming")
            if (cbMusik.isChecked) hobis.add("Musik")
            if (cbOlahraga.isChecked) hobis.add("Olahraga")
            if (cbMembaca.isChecked) hobis.add("Membaca")
            if (cbKoding.isChecked) hobis.add("Coding")

            if (hobis.isEmpty()) {
                Snackbar.make(it, "Pilih minimal satu hobi", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userData = Mahasiswa(
                namaLengkap = nama,
                nim = nim,
                programStudi = prodiSelected,
                jenisKelamin = jenisKelamin,
                hobi = hobis
            )

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USER_DATA", userData)
            startActivity(intent)
        }
    }
}

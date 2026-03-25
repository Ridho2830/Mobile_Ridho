package psti.unram.tugas2

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Binding
        val etMatkul = findViewById<EditText>(R.id.etMatkul)
        val etNilai = findViewById<EditText>(R.id.etNilai)
        val btnTambah = findViewById<Button>(R.id.btnTambah)
        val btnHitung = findViewById<Button>(R.id.btnHitung)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val listView = findViewById<ListView>(R.id.listView)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // Array untuk nyimpan data
        val listMatkul = ArrayList<String>()
        val listNilai = ArrayList<Double>()

        // Adapter untuk menampilkan hasil ke layar
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listMatkul
        )
        listView.adapter = adapter

        // Logic Tambah
        btnTambah.setOnClickListener {

            val matkul = etMatkul.text.toString()
            val nilaiStr = etNilai.text.toString()

            // Validasi kosong
            if (matkul.isEmpty() || nilaiStr.isEmpty()) {
                Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi angka
            val nilaiInt = nilaiStr.toIntOrNull()
            if (nilaiInt == null) {
                Toast.makeText(this, "Nilai harus angka!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi range nilai
            if (nilaiInt < 0 || nilaiInt > 100) {
                Toast.makeText(this, "Nilai harus 0 - 100!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var bobot = 0.0
            var bobotStr = ""


            if (nilaiInt >= 85){
                bobot = 4.0
                bobotStr = "A"
            }
            else if (nilaiInt <= 84 && nilaiInt >= 80){
                bobot = 3.5
                bobotStr = "B+"
            }
            else if (nilaiInt >= 75 && nilaiInt < 80){
                bobot = 3.0
                bobotStr = "B"
            }
            else if (nilaiInt >= 70 && nilaiInt < 75){
                bobot = 2.5
                bobotStr = "C+"
            }
            else if (nilaiInt >= 65 && nilaiInt < 70){
                bobot = 2.0
                bobotStr = "C"
            }
            else if (nilaiInt >= 60 && nilaiInt < 65){
                bobot = 1.5
                bobotStr = "D+"
            }
            else if (nilaiInt >= 55 && nilaiInt < 60){
                bobot = 1.0
                bobotStr = "D"
            }
            else bobot = 0.0

            listMatkul.add("$matkul : $nilaiInt ($bobotStr)")
            listNilai.add(bobot)

            adapter.notifyDataSetChanged()

            etMatkul.text.clear()
            etNilai.text.clear()
        }


        // Logic Hitung
        btnHitung.setOnClickListener {

            if (listNilai.size == 0) {
                Toast.makeText(this, "Belum ada data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var total = 0.0

            for (data in listNilai) {
                total += data
            }

            val ipk = total / listNilai.size

            // Format desimal
            tvHasil.text = "IPK : %.2f".format(ipk)
        }

        // Logic Reset
        btnReset.setOnClickListener {
            listMatkul.clear()
            listNilai.clear()
            adapter.notifyDataSetChanged()
            tvHasil.text = "IPK : -"
            etMatkul.text.clear()
            etNilai.text.clear()
        }

    }
}

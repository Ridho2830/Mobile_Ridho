package psti.unram.parcelabel

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubActivity2 : AppCompatActivity() {
    companion object {
        const val EXTRA_STRING = "EXTRA_STRING"
        const val EXTRA_PARCEL = "EXTRA_PARCEL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub2)

        val tvData : TextView = findViewById(R.id.tvData)

        val strData = intent.getStringExtra(EXTRA_STRING)
        val obj = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_PARCEL, Mahasiswa::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Mahasiswa>(EXTRA_PARCEL)
        }

        val text = if (!strData.isNullOrBlank()) {
            strData
        } else if (obj != null) {
            "NIM: ${obj.nim}, Nama: ${obj.nama}"
        } else {
            "Tidak ada data!"
        }

        tvData.text = text
    }
}
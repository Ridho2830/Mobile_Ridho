package psti.unram.tugas3

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("USER_DATA", Mahasiswa::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Mahasiswa>("USER_DATA")
        }

        userData?.let {
            val initial = it.namaLengkap.firstOrNull()?.uppercaseChar()?.toString() ?: "?"
            findViewById<TextView>(R.id.tvAvatarInitial).text = initial

            findViewById<TextView>(R.id.tvNama).text = it.namaLengkap
            findViewById<TextView>(R.id.tvNim).text = "NIM: ${it.nim}"
            findViewById<TextView>(R.id.tvProdi).text = it.programStudi
            findViewById<TextView>(R.id.tvJenisKelamin).text = it.jenisKelamin

            val chipGroup = findViewById<ChipGroup>(R.id.chipGroupHobi)
            it.hobi.forEach { hobi ->
                val chip = Chip(this)
                chip.text = hobi
                chip.isClickable = false
                chip.isCheckable = false
                chip.setChipBackgroundColorResource(R.color.cyan_accent)
                chip.setTextColor(resources.getColor(R.color.bg_dark, theme))
                chip.chipStrokeWidth = 0f
                chipGroup.addView(chip)
            }
        }

        findViewById<android.widget.ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}

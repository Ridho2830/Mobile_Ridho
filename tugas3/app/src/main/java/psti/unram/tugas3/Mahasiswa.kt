package psti.unram.tugas3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mahasiswa(
    val namaLengkap: String,
    val nim: String,
    val programStudi: String,
    val jenisKelamin: String,
    val hobi: List<String>
) : Parcelable

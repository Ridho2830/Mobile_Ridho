package psti.unram.parcelabel

import kotlinx.parcelize.Parcelize
import android.os.Parcelable



@Parcelize
data class Mahasiswa(
    val nim : String,
    val nama: String
) : Parcelable
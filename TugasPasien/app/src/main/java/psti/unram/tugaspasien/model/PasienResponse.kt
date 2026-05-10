package psti.unram.tugaspasien.model

import com.google.gson.annotations.SerializedName

data class PasienResponse(
    val success: Boolean?,
    val message: String?,
    val data: List<Pasien>?
)

data class Pasien(
    val id: Int?,
    val nama: String?,
    @SerializedName("tanggal_lahir")
    val tanggalLahir: String?,
    @SerializedName("jenis_kelamin")
    val jenisKelamin: String?,
    val alamat: String?,
    @SerializedName("no_telepon")
    val noTelepon: String?
)

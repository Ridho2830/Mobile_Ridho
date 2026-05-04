package psti.unram.tugasstorage.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import psti.unram.tugasstorage.Mahasiswa

@Entity(tableName = "mahasiswa")
data class MahasiswaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nim: String,
    val nama: String,
    val jurusan: String,
    val ipk: Double
){
    fun toMahasiswa() = Mahasiswa(
        id = id,
        nim = nim,
        nama = nama,
        jurusan = jurusan,
        ipk = ipk
    )
    companion object{
        fun fromMahasiswa(mahasiswa: Mahasiswa) = MahasiswaEntity(
            id = mahasiswa.id,
            nim = mahasiswa.nim,
            nama = mahasiswa.nama,
            jurusan = mahasiswa.jurusan,
            ipk = mahasiswa.ipk
        )
    }
}

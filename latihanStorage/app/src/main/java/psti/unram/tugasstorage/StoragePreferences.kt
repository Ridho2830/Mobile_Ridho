package psti.unram.tugasstorage

import android.content.Context
import android.content.SharedPreferences

class StoragePreferences(context: Context) : StorageMahasiswaInterface {
    private val pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun save(mahasiswa: Mahasiswa) {
        with(pref.edit()) {
            putString(DATA_NIM, mahasiswa.nim)
            putString(DATA_NAMA, mahasiswa.nama)
            apply()
        }
    }

    override fun get(): Mahasiswa {
        val nim = pref.getString(DATA_NIM, "") ?: ""
        val nama = pref.getString(DATA_NAMA, "") ?: ""
        return Mahasiswa(nim, nama)
    }

    override fun delete() {
        pref.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "data_pref"
        private const val DATA_NIM = "nim"
        private const val DATA_NAMA = "nama"
    }
}

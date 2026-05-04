package psti.unram.tugasstorage

import android.content.Context
import java.lang.Exception

class StorageSpecific(private val context: Context) : StorageMahasiswaInterface {

    override fun save(mahasiswa: Mahasiswa) {
        val data = "${mahasiswa.nim}|${mahasiswa.nama}"
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    override fun get(): Mahasiswa {
        return try {
            val text = context.openFileInput(FILE_NAME).bufferedReader().use { it.readText() }
            val res = text.split("|")
            Mahasiswa(
                nim = res.getOrNull(0) ?: "",
                nama = res.getOrNull(1) ?: ""
            )
        } catch (e: Exception) {
            Mahasiswa()
        }
    }

    override fun delete() {
        context.deleteFile(FILE_NAME)
    }

    companion object {
        private const val FILE_NAME = "file_data"
    }
}

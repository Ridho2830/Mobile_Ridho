package psti.unram.tugasstorage.room

import android.content.Context
import psti.unram.tugasstorage.Mahasiswa
import psti.unram.tugasstorage.StorageMahasiswaInterface

class StorageRoom(content: Context) : StorageMahasiswaInterface {
    private val dao = AppDatabase.getDatabase(content).mahasiswaDao()

    override suspend fun insert(mahasiswa: Mahasiswa) {
        dao.insert(MahasiswaEntity.fromMahasiswa(mahasiswa))
    }

    override suspend fun getAll(): List<Mahasiswa> {
        return dao.getAll().map { it.toMahasiswa() }
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }
}

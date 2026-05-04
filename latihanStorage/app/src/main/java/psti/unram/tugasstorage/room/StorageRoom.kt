package psti.unram.tugasstorage.room

import android.content.Context
import androidx.room.Room
import psti.unram.tugasstorage.Mahasiswa
import psti.unram.tugasstorage.StorageMahasiswaInterface

class StorageRoom(context: Context) : StorageMahasiswaInterface {
    private val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "mahasiswa_db"
    )
    .allowMainThreadQueries()
    .build()

    override fun save(mahasiswa: Mahasiswa) {
        val entity = MahasiswaEntity(
            nim = mahasiswa.nim ?: "",
            nama = mahasiswa.nama ?: ""
        )
        db.mahasiswaDao().insert(entity)
    }

    override fun get(): Mahasiswa {
        val list: List<MahasiswaEntity> = db.mahasiswaDao().getAll()

        if (list.isNotEmpty()) {
            val lastEntity = list.last()
            return Mahasiswa(
                nim = lastEntity.nim,
                nama = lastEntity.nama
            )
        }
        return Mahasiswa()
    }

    override fun delete() {
        db.mahasiswaDao().deleteAll()
    }
}

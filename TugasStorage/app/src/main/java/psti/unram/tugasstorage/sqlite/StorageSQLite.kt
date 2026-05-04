package psti.unram.tugasstorage.sqlite

import android.content.ContentValues
import android.content.Context
import psti.unram.tugasstorage.Mahasiswa
import psti.unram.tugasstorage.StorageMahasiswaInterface

class StorageSQLite(context: Context) : StorageMahasiswaInterface {
    private val dbHelper = MahasiswaDBHelper(context)

    override suspend fun insert(mahasiswa: Mahasiswa) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDB.MahasiswaEntry.COLUMN_NAME_NIM, mahasiswa.nim)
            put(MyDB.MahasiswaEntry.COLUMN_NAME_NAMA, mahasiswa.nama)
            put(MyDB.MahasiswaEntry.COLUMN_NAME_JURUSAN, mahasiswa.jurusan)
            put(MyDB.MahasiswaEntry.COLUMN_NAME_IPK, mahasiswa.ipk)
        }
        db.insert(MyDB.MahasiswaEntry.TABLE_NAME, null, values)
    }

    override suspend fun getAll(): List<Mahasiswa> {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            android.provider.BaseColumns._ID,
            MyDB.MahasiswaEntry.COLUMN_NAME_NIM,
            MyDB.MahasiswaEntry.COLUMN_NAME_NAMA,
            MyDB.MahasiswaEntry.COLUMN_NAME_JURUSAN,
            MyDB.MahasiswaEntry.COLUMN_NAME_IPK
        )
        val cursor = db.query(
            MyDB.MahasiswaEntry.TABLE_NAME,
            projection,
            null, null, null, null, null
        )
        val list = mutableListOf<Mahasiswa>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(android.provider.BaseColumns._ID))
                val nim = getString(getColumnIndexOrThrow(MyDB.MahasiswaEntry.COLUMN_NAME_NIM))
                val nama = getString(getColumnIndexOrThrow(MyDB.MahasiswaEntry.COLUMN_NAME_NAMA))
                val jurusan = getString(getColumnIndexOrThrow(MyDB.MahasiswaEntry.COLUMN_NAME_JURUSAN))
                val ipk = getDouble(getColumnIndexOrThrow(MyDB.MahasiswaEntry.COLUMN_NAME_IPK))
                list.add(Mahasiswa(id, nim, nama, jurusan, ipk))
            }
        }
        cursor.close()
        return list
    }

    override suspend fun delete(id: Int) {
        val db = dbHelper.writableDatabase
        val selection = "${android.provider.BaseColumns._ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        db.delete(MyDB.MahasiswaEntry.TABLE_NAME, selection, selectionArgs)
    }
}

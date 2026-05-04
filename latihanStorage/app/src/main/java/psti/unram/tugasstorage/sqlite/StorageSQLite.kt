package psti.unram.tugasstorage.sqlite

import android.content.ContentValues
import android.content.Context
import psti.unram.tugasstorage.Mahasiswa
import psti.unram.tugasstorage.StorageMahasiswaInterface

class StorageSQLite(context: Context) : StorageMahasiswaInterface {
    private val dbHelper = MahasiswaDBHelper(context)

    override fun save(mahasiswa: Mahasiswa) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDB.TabelMahasiswa.COLUMN_NIM, mahasiswa.nim)
            put(MyDB.TabelMahasiswa.COLUMN_NAMA, mahasiswa.nama)
        }
        db.insert(MyDB.TabelMahasiswa.TABLE_NAME, null, values)
    }

    override fun get(): Mahasiswa {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            MyDB.TabelMahasiswa.COLUMN_NIM,
            MyDB.TabelMahasiswa.COLUMN_NAMA
        )

        val cursor = db.query(
            MyDB.TabelMahasiswa.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        var nim = ""
        var nama = ""
        if (cursor.moveToLast()) {
            nim = cursor.getString(cursor.getColumnIndexOrThrow(MyDB.TabelMahasiswa.COLUMN_NIM)) ?: ""
            nama = cursor.getString(cursor.getColumnIndexOrThrow(MyDB.TabelMahasiswa.COLUMN_NAMA)) ?: ""
        }
        cursor.close()
        return Mahasiswa(nim, nama)
    }

    override fun delete() {
        val db = dbHelper.writableDatabase
        db.delete(MyDB.TabelMahasiswa.TABLE_NAME, null, null)
    }
}

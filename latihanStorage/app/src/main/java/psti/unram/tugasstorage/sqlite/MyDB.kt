package psti.unram.tugasstorage.sqlite

import android.provider.BaseColumns

object MyDB {
    object TabelMahasiswa : BaseColumns {
        const val TABLE_NAME = "mahasiswa"
        const val COLUMN_NIM = "nim"
        const val COLUMN_NAMA = "nama"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${TabelMahasiswa.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TabelMahasiswa.COLUMN_NIM} TEXT," +
                "${TabelMahasiswa.COLUMN_NAMA} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TabelMahasiswa.TABLE_NAME}"
}

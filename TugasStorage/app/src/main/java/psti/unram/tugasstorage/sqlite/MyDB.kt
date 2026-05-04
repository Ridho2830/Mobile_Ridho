package psti.unram.tugasstorage.sqlite

import android.provider.BaseColumns

object MyDB {
    class MahasiswaEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "mahasiswa_sqlite"
            const val COLUMN_NAME_NIM = "nim"
            const val COLUMN_NAME_NAMA = "nama"
            const val COLUMN_NAME_JURUSAN = "jurusan"
            const val COLUMN_NAME_IPK = "ipk"
        }
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${MahasiswaEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${MahasiswaEntry.COLUMN_NAME_NIM} TEXT," +
                "${MahasiswaEntry.COLUMN_NAME_NAMA} TEXT," +
                "${MahasiswaEntry.COLUMN_NAME_JURUSAN} TEXT," +
                "${MahasiswaEntry.COLUMN_NAME_IPK} REAL)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${MahasiswaEntry.TABLE_NAME}"
}

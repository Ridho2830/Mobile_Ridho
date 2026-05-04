package psti.unram.tugasstorage

import android.content.Context

class StoragePreferences(context: Context) {

    private val pref = context.getSharedPreferences("my_app_pref", Context.MODE_PRIVATE)

    var jurusanTerakhir: String
        get() = pref.getString("jurusan_terakhir", "") ?: ""
        set(value) = pref.edit().putString("jurusan_terakhir", value).apply()
}

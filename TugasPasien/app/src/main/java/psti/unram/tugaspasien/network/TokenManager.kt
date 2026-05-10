package psti.unram.tugaspasien.network

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveAuthData(token: String, userName: String) {
        val editor = prefs.edit()
        editor.putString("USER_TOKEN", token)
        editor.putString("USER_NAME", userName)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString("USER_TOKEN", null)
    }

    fun getUserName(): String? {
        return prefs.getString("USER_NAME", null)
    }

    fun clearAuthData() {
        prefs.edit().clear().apply()
    }
}

package pl.project.ecommerceapp.Api

import android.content.Context
import android.content.SharedPreferences
import pl.project.ecommerceapp.R

class SessionManager (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }


    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        val token2 = "Bearer $token"
        editor.putString(USER_TOKEN, token2)
        editor.apply()

    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}
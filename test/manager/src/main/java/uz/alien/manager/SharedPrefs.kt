package uz.alien.manager

import android.content.Context
import android.content.SharedPreferences

object SharedPrefs {

    /*
    * SharedPrefs manager is 100% ready for use any project, please don't change it!!!
    */

    private const val MESSAGE = "SharedPrefs has not been initialized. Please init SharedPrefs in your Activity class!!!"
    @Volatile private var preferences: SharedPreferences? = null

    private fun checkPreferences(): SharedPreferences {
        return preferences ?: throw Exception(MESSAGE)
    }

    fun init(context: Context, prefsName: String) {
        preferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    fun saveValue(name: String, value: Any) {
        val prefs = checkPreferences()
        synchronized(this) {
            with(prefs.edit()) {
                when (value) {
                    is Int -> putInt(name, value)
                    is Long -> putLong(name, value)
                    is Float -> putFloat(name, value)
                    is String -> putString(name, value)
                    is Boolean -> putBoolean(name, value)
                    else -> throw IllegalArgumentException("Unsupported value type")
                }.apply()
            }
        }
    }

    fun getInt(name: String, default: Int = 0): Int {
        return checkPreferences().getInt(name, default)
    }

    fun getLong(name: String, default: Long = 0L): Long {
        return checkPreferences().getLong(name, default)
    }

    fun getFloat(name: String, default: Float = 0.0f): Float {
        return checkPreferences().getFloat(name, default)
    }

    fun getString(name: String, default: String? = null): String? {
        return checkPreferences().getString(name, default)
    }

    fun getBoolean(name: String, default: Boolean = false): Boolean {
        return checkPreferences().getBoolean(name, default)
    }
}
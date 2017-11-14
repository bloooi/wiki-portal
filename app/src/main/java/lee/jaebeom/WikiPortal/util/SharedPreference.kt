package lee.jaebeom.WikiPortal.util

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.preference.PreferenceManager

/**
 * Created by leejaebeom on 2017. 10. 31..
 */
class SharedPreference {
    companion object {
        const val keyKeyword: String = "keyword"
        const val keySequence: String = "sequence"
        private lateinit var pref: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        @JvmStatic fun savePreference(key: String, value: String, context: Context){
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            editor = pref.edit()
            editor.putString(key, value)
            editor.apply()
        }

        @JvmStatic fun savePreference(key: String, value: Int, context: Context){
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            editor = pref.edit()
            editor.putInt(key, value)
            editor.apply()
        }

        @JvmStatic fun savePreference(key: String, value: Boolean, context: Context){
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            editor = pref.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        @JvmStatic fun getStringPreferences(context: Context, key: String) : String{
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getString(key, "")
        }

        @JvmStatic fun getIntPreferences(context: Context, key: String) : Int{
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getInt(key, 0)
        }

        @JvmStatic fun getBooleanPreferences(context: Context, key: String): Boolean {
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getBoolean(key, false)
        }
    }
}
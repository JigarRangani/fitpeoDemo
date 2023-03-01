package com.fitpeo.imagedemo.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceManager @Inject constructor(@ApplicationContext context: Context) {
    private val NAME = "demo"
    private val MODE = Context.MODE_PRIVATE
    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)

    val prefs = context.getSharedPreferences(NAME, MODE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        get() = prefs.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        set(value) = prefs.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }


    fun deleteAll() {
        prefs.edit().clear().apply()
    }
}
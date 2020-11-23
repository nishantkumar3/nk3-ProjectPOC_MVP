package com.example.projectpoc.sessionManager

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.projectpoc.user.userView.MainActivity

class UserSessionManager(var context: Context) {
    private var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var PRIVATE_MODE: Int = 0


    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    companion object {
        const val PREF_NAME: String = "FinalPocProject"
        const val IS_LOGIN: String = "isLoggedIn"
        const val KEY_USERID: String = "userId"
    }

    fun createLoginSession(userId: Int) {
        editor = pref.edit()
        editor.putBoolean(IS_LOGIN, true)
        editor.putInt(KEY_USERID, userId)
        editor.apply()
    }


    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }

    fun logoutUser() {
        editor = pref.edit()
        editor.clear()
        editor.apply()
    }

    fun getUserDetails(): Int {
        return pref.getInt(KEY_USERID, 0)
    }

}
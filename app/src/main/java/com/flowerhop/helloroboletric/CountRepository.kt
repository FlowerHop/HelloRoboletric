package com.flowerhop.helloroboletric

import android.content.Context
import android.content.SharedPreferences

class CountRepository private constructor(private val applicationContext: Context) {
    companion object {
        private const val SHARED_PREF_FILE_NAME = "CountRepository"
        private const val SHARED_PREF_KEY_COUNT = "count"
        private const val DEFAULT_COUNT = 0

        fun create(context: Context): CountRepository {
            return CountRepository(context)
        }
    }

    private var sharedCount = 0

    fun loadCount(): Int =
        getShared().getInt(SHARED_PREF_KEY_COUNT, DEFAULT_COUNT)

    fun updateCount(count: Int) {
        sharedCount = count
        getShared().edit().putInt(SHARED_PREF_KEY_COUNT, sharedCount).apply()
    }

    private fun getShared(): SharedPreferences {
        return applicationContext.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
}

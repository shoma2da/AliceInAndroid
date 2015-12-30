package io.github.shoma2da.android.aliceinandroid.model

import android.content.Context

/**
 * Created by shoma2da on 2015/12/30.
 */
class ShareRestriction(private val context:Context, private val story:Story) {

    companion object {
        const val FILE_NAME = "share_restriction"
        private const val KEY = "done_share"

        fun reset(context:Context) {
            val pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }
    }

    fun isValid():Boolean {
        if (story.number < 5) {
            return true
        }

        val pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return pref.getBoolean(KEY, false)
    }

    fun setValid() {
        val pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        pref.edit().putBoolean(KEY, true).apply()
    }
}
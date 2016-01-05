package io.github.shoma2da.android.aliceinandroid.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.Serializable

/**
 * Created by shoma2da on 2015/12/30.
 */
class Story(val number:Int, val title:String, val resourceId:Int) :Serializable {

    companion object {
        const val FILE_NAME = "story_progresses"
        const val EMPTY_PROGRESS_VALUE = -1
    }

    val listTitle = when(number <= 0) {
        true -> title
        false -> "第 $number 章　$title"
    }

    fun saveProgress(context:Context, progress:Int) {
        val pref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        pref.edit().putInt("$number", progress).apply()
    }

    fun loadProgress(context:Context):Int {
        val pref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        return pref.getInt("$number", EMPTY_PROGRESS_VALUE)
    }

    fun isHaveNotProgress(context:Context) = (loadProgress(context) == EMPTY_PROGRESS_VALUE)
}
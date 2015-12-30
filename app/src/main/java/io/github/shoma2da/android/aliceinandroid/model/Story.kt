package io.github.shoma2da.android.aliceinandroid.model

import android.support.annotation.LayoutRes
import java.io.Serializable

/**
 * Created by shoma2da on 2015/12/30.
 */
class Story(val number:Int, val title:String, val resourceId:Int) :Serializable {
    fun listTitle():String = when(number < 0) {
        true -> title
        false -> "第 ${number} 章　$title"
    }
}
package io.github.shoma2da.android.aliceinandroid.extensions

import android.util.Log
import android.widget.ScrollView

/**
 * Created by shoma2da on 2015/12/30.
 */
fun ScrollView.progressPercent():Int {
    val child = this.getChildAt(0)
    return when (child == null) {
        true -> 0
        false -> (this.height + this.scrollY) * 100 / child.height
    }
}

fun ScrollView.scrollTo(progress:Int) {
    val child = this.getChildAt(0)
    when (child == null) {
        true -> {}
        false -> this.scrollTo(0, (child.height * progress / 100) - this.height)
    }
}

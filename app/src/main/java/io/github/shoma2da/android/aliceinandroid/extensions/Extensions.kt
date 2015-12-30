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


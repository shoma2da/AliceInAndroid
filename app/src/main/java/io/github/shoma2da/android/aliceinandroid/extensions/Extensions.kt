package io.github.shoma2da.android.aliceinandroid.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import java.util.*
import kotlin.collections.arrayListOf
import kotlin.collections.linkedListOf
import kotlin.collections.listOf
import kotlin.collections.toList

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

fun Context.getMyPackageVersionName():String {
    val info = packageManager.getPackageInfo(packageName, 0);
    return info.versionName;
}

fun Context.shareToTwitter() {
    val message = "「不思議の国のアリス」を無料で読めた。イラストも入ってて雰囲気も良いね！ https://play.google.com/store/apps/details?id=io.github.shoma2da.android.aliceinandroid"
    val url = "http://twitter.com/share?text=$message"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url));
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent);
}

fun ViewGroup.getChildren():List<View> {
    val list = arrayListOf<View>()
    for (i in 0..childCount) {
        val child = getChildAt(i)
        list.add(child)
    }
    return list.toList()
}

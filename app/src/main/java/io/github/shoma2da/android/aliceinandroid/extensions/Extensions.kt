package io.github.shoma2da.android.aliceinandroid.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
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

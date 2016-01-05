package io.github.shoma2da.android.aliceinandroid

import android.app.Application
import co.meyasuba.android.sdk.Meyasubaco
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import io.fabric.sdk.android.Fabric;

/**
 * Created by shoma2da on 2015/12/30.
 */
class AliceApplication : Application() {

    private var mTracker:Tracker? = null

    override fun onCreate() {
        super.onCreate()
        Meyasubaco.setApiKey(this, "e0da968afdf2042b1620b104587070d746b6402d2f4d05d17caec2a9f843575a")
        Fabric.with(this, Crashlytics());
    }

    fun getDefaultTracker():Tracker {
        val tracker = mTracker
        if (tracker != null) {
            return tracker
        }

        val analytics = GoogleAnalytics.getInstance(this);
        val newTracker = analytics.newTracker("UA-32548600-17")
        mTracker = newTracker
        return newTracker
    }
}

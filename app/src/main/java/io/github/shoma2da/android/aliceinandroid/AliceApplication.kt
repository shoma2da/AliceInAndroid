package io.github.shoma2da.android.aliceinandroid

import android.app.Application
import co.meyasuba.android.sdk.Meyasubaco

/**
 * Created by shoma2da on 2015/12/30.
 */
class AliceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Meyasubaco.setApiKey(this, "e0da968afdf2042b1620b104587070d746b6402d2f4d05d17caec2a9f843575a")
    }
}

package io.github.shoma2da.android.aliceinandroid.model

import android.content.Context
import io.github.shoma2da.android.aliceinandroid.R
import kotlin.collections.arrayListOf
import kotlin.collections.forEach

/**
 * Created by shoma2da on 2015/12/30.
 */
class Stories {
    companion object {
        val list = arrayListOf(
                Story(0, "巻頭詩", R.layout.layout_prologue),
                Story(1, "うさぎの穴をまっさかさま", R.layout.layout_1st),
                Story(2, "涙の池", R.layout.layout_prologue),
                Story(3, "がくがくかけっことながいお話", R.layout.layout_prologue),
                Story(4, "うさぎ、小さなビルをおくりこむ", R.layout.layout_prologue),
                Story(5, "いもむしの忠告", R.layout.layout_prologue),
                Story(6, "ぶたとコショウ", R.layout.layout_prologue),
                Story(7, "キチガイお茶会", R.layout.layout_prologue),
                Story(8, "女王さまのクロケー場", R.layout.layout_prologue),
                Story(9, "にせウミガメのお話", R.layout.layout_prologue),
                Story(10, "ロブスターのカドリーユおどり", R.layout.layout_prologue),
                Story(11, "タルトをぬすんだのはだれ？", R.layout.layout_prologue),
                Story(12, "アリスのしょうこ", R.layout.layout_prologue)
        )

        fun reset(context:Context) {
            val pref = context.getSharedPreferences(Story.FILE_NAME, Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }
    }
}
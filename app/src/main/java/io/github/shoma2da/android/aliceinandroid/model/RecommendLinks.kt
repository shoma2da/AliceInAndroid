package io.github.shoma2da.android.aliceinandroid.model

import java.util.*
import kotlin.collections.arrayListOf

/**
 * Created by shoma2da on 2015/12/30.
 */
class RecommendLinks {

    val urls = arrayListOf(
            /*アリスの棘*/ "http://www.amazon.co.jp/gp/product/B00KWUVIK4/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=B00KWUVIK4&linkCode=as2&tag=shoma2da-22",
            /*ARMS*/ "http://www.amazon.co.jp/gp/product/B00AQ9H7MO/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=B00AQ9H7MO&linkCode=as2&tag=shoma2da-22"
    )

    fun getRandom() = urls.get(Random().nextInt(urls.size))

}
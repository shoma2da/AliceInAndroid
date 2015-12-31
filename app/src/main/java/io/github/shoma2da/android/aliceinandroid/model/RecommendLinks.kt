package io.github.shoma2da.android.aliceinandroid.model

import java.util.*
import kotlin.collections.arrayListOf

/**
 * Created by shoma2da on 2015/12/30.
 */
class RecommendLinks {

    val urls = arrayListOf(
            /*アリスの棘*/ "http://www.amazon.co.jp/gp/product/B00KWUVIK4/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=B00KWUVIK4&linkCode=as2&tag=shoma2da-22",
            /*ARMS*/ "http://www.amazon.co.jp/gp/product/B00AQ9H7MO/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=B00AQ9H7MO&linkCode=as2&tag=shoma2da-22",
            /*黒猫館の殺人*/ "http://www.amazon.co.jp/gp/product/4062632780/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=4062632780&linkCode=as2&tag=shoma2da-22",
            /*アリス・イン・ワンダーランド*/ "http://www.amazon.co.jp/gp/product/B003OFC2WM/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=B003OFC2WM&linkCode=as2&tag=shoma2da-22",
            /*ディアデュイット*/ "http://www.amazon.co.jp/gp/product/B00R6ZFEDU/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=B00R6ZFEDU&linkCode=as2&tag=shoma2da-22",
            /*Taylor Swift*/ "http://www.amazon.co.jp/gp/product/B00OY1A8K8/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=B00OY1A8K8&linkCode=as2&tag=shoma2da-22",
            /*ワンダーランド*/ "http://www.amazon.co.jp/gp/product/4091872980/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=4091872980&linkCode=as2&tag=shoma2da-22",
            /*コミックアンソロジー極 不思議の国のアリス*/ "http://www.amazon.co.jp/gp/product/4757538081/ref=as_li_ss_tl?ie=UTF8&camp=247&creative=7399&creativeASIN=4757538081&linkCode=as2&tag=shoma2da-22"
    )

    fun getRandom() = urls.get(Random().nextInt(urls.size))

}
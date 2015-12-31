package io.github.shoma2da.android.aliceinandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import io.github.shoma2da.android.aliceinandroid.extensions.getChildren
import io.github.shoma2da.android.aliceinandroid.extensions.progressPercent
import io.github.shoma2da.android.aliceinandroid.extensions.scrollTo
import io.github.shoma2da.android.aliceinandroid.extensions.shareToTwitter
import io.github.shoma2da.android.aliceinandroid.model.ShareRestriction
import io.github.shoma2da.android.aliceinandroid.model.Story
import kotlin.collections.filter
import kotlin.collections.forEach

/**
 * Created by shoma2da on 2015/12/29.
 */
class DetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_KEY_STORY = "DetailActivity.STORY"

        fun start(context:Context, story:Story) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PARAM_KEY_STORY, story)
            context.startActivity(intent)
        }
    }

    private var mContentView:ScrollView? = null
    private var mStory:Story? = null
    private var mInterstitial:InterstitialAd? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //インタースティシャル広告の用意
        mInterstitial = InterstitialAd(this);
        mInterstitial?.adUnitId = getString(R.string.interstitial_ad_unit_id)
        val adRequest = AdRequest.Builder().build();
        mInterstitial?.loadAd(adRequest);
        mInterstitial?.adListener = object:AdListener(){
            override fun onAdClosed() {
                finish()
            }
        }

        val story = intent.getSerializableExtra(PARAM_KEY_STORY) as Story?
        mStory = story

        if (story != null) {
            checkShareRestriction(story)
            setupViews(story)
        } else {
            Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }

    private fun checkShareRestriction(story:Story) {
        //シェア制限をかける
        val restriction = ShareRestriction(this, story)
        if (restriction.isValid()) {
            return
        }

        AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("シェアしてください")
                .setMessage("ここから先の章を読むにはTwitterにシェアが必要です。「シェアする」を選択してください。")
                .setPositiveButton("シェアする", { dialog, which ->
                    shareToTwitter()
                    restriction.setValid()
                    true
                })
                .setNegativeButton("キャンセル", { dialog, which ->
                    finish()
                })
                .show()
    }

    private fun setupViews(story:Story) {
        //Toolbarを初期化
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = story.title

        //表示内容を初期化
        val listView = findViewById(R.id.content) as ScrollView
        LayoutInflater.from(this).inflate(story.resourceId, listView, true)
        mContentView = listView

        //スクロール位置の初期設定
        listView.post {
            val progress = story.loadProgress(this)
            listView.scrollTo(progress)
        }

        //スクロール時の動作設定
        listView.setOnScrollChangeListener({ view, x, y, oldX, oldY ->
            supportActionBar!!.title = "${story.title}（${listView.progressPercent()}%）"
        })

        //設定によっては文字を大きくする
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val needEnlarge = pref.getBoolean("enlarge_character_size", false)
        if (needEnlarge) {
            val scrollTopView = listView.getChildAt(0) as ViewGroup
            scrollTopView.getChildren().forEach { child ->
                if (child is TextView) {
                    val sizeInPixel = child.textSize
                    child.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeInPixel * 1.2f)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()

        val list = mContentView
        val story = mStory
        if (story != null && list != null) {
            story.saveProgress(this, list.progressPercent())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val interstitial = mInterstitial ?: return

        if (interstitial.isLoaded) {
            interstitial.show()
        } else {
            super.onBackPressed()
        }
    }
}

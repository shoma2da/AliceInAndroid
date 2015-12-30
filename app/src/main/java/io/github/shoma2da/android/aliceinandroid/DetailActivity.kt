package io.github.shoma2da.android.aliceinandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ScrollView
import android.widget.Toast
import io.github.shoma2da.android.aliceinandroid.extensions.*
import io.github.shoma2da.android.aliceinandroid.model.Story

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

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val story = intent.getSerializableExtra(PARAM_KEY_STORY) as Story?
        mStory = story

        if (story != null) {
            setupViews(story)
        } else {
            Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }

    private fun setupViews(story:Story) {
        //Toolbarを初期化
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = story?.title

        //表示内容を初期化
        mContentView = findViewById(R.id.content) as ScrollView
        LayoutInflater.from(this).inflate(story.resourceId, mContentView, true)

        //スクロール位置の初期設定
        mContentView?.post {
            val progress = story?.loadProgress(this)
            mContentView?.scrollTo(progress)
        }

        //スクロール時の動作設定
        mContentView?.setOnScrollChangeListener({ view, x, y, oldX, oldY ->
            supportActionBar!!.title = "${story?.title}（${mContentView?.progressPercent()}%）"
        })
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
}

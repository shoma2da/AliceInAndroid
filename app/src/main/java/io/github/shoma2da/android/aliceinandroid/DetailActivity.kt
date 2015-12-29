package io.github.shoma2da.android.aliceinandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
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

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val story = intent.getSerializableExtra(PARAM_KEY_STORY) as Story?

        if (story != null) {
            val toolbar = findViewById(R.id.toolbar) as Toolbar
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = story?.title

            val content = findViewById(R.id.content) as ViewGroup
            LayoutInflater.from(this).inflate(story.resourceId, content, true)
        } else {
            Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_SHORT).show()
            onBackPressed()
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

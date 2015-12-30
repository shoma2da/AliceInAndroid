package io.github.shoma2da.android.aliceinandroid

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import co.meyasuba.android.sdk.Meyasubaco
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import io.github.shoma2da.android.aliceinandroid.model.Stories
import io.github.shoma2da.android.aliceinandroid.model.Story

class MainActivity : AppCompatActivity() {

    private var mListView:ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val listView = findViewById(R.id.list_story) as ListView
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val story = view.tag as Story
            DetailActivity.start(this, story)
        }
        mListView = listView

        //広告の設定
        val adView = findViewById(R.id.adView) as AdView;
        val adRequest = AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    override fun onResume() {
        super.onResume()

        //リストの表示
        val adapter = StoryAdapter(this, Stories.list)
        mListView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_help -> {
                Meyasubaco.showCommentActivity(this)
                return true
            }
            R.id.menu_setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

class StoryAdapter(context:Context, stories:List<Story>) : ArrayAdapter<Story>(context, android.R.layout.simple_list_item_1, stories) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = super.getView(position, convertView, parent) as TextView

        val story = getItem(position)
        view.tag = story
        view.text = story.listTitle

        //未読・既読表示
        val typeface = if (story.isHaveNotProgress(context)) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        view.typeface = typeface

        return view
    }
}

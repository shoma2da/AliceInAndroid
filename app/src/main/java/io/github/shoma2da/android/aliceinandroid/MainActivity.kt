package io.github.shoma2da.android.aliceinandroid

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import io.github.shoma2da.android.aliceinandroid.model.Story
import kotlin.collections.arrayListOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val storyies = arrayListOf(
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

        val listView = findViewById(R.id.list_story) as ListView
        listView.adapter = StoryAdapter(this, storyies)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val story = view.tag as Story
            DetailActivity.start(this, story)
        }
    }
}

class StoryAdapter(context:Context, stories:List<Story>) : ArrayAdapter<Story>(context, android.R.layout.simple_list_item_1, stories) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = super.getView(position, convertView, parent) as TextView
        view.text = getItem(position).listTitle
        view.tag = getItem(position)
        return view
    }
}

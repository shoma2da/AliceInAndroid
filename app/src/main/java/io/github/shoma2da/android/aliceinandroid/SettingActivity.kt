package io.github.shoma2da.android.aliceinandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import co.meyasuba.android.sdk.Meyasubaco
import io.github.shoma2da.android.aliceinandroid.extensions.getMyPackageVersionName
import io.github.shoma2da.android.aliceinandroid.extensions.shareToTwitter
import io.github.shoma2da.android.aliceinandroid.model.RecommendLinks
import io.github.shoma2da.android.aliceinandroid.model.Stories

/**
 * Created by shoma2da on 2015/12/30.
 */
class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "設定"

        findViewById(R.id.text_recommend).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(RecommendLinks().getRandom()))
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
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

class SettingFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences);

        //初期化の設定
        findPreference("initialize").setOnPreferenceClickListener {
            AlertDialog.Builder(activity)
                    .setTitle("初期化する")
                    .setMessage("読書履歴などが削除されます。よろしいですか？")
                    .setPositiveButton("初期化する", { dialog, which ->
                        Stories.reset(activity)
                    })
                    .setNegativeButton("キャンセル", null)
                    .show()
            true
        }

        findPreference("request").setOnPreferenceClickListener {
            Meyasubaco.showCommentActivity(activity)
            true
        }
        findPreference("question").setOnPreferenceClickListener {
            Meyasubaco.showHelpListActivity(activity)
            true
        }
        findPreference("version").summary = activity.getMyPackageVersionName()
        findPreference("copyright").setOnPreferenceClickListener { pref ->
            AlertDialog.Builder(activity)
                    .setTitle(pref.title)
                    .setMessage(R.string.copyright)
                    .setPositiveButton("OK", { dialog, which ->
                        Stories.reset(activity)
                    })
                    .show()
            true
        }
        findPreference("share").setOnPreferenceClickListener {
            activity.shareToTwitter()
            true
        }
    }

}

package com.ost.rj.mge.testat.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ost.rj.mge.testat.R

class FeedItemDetailActivity : AppCompatActivity() {

    private lateinit var title : String
    private lateinit var tags : String

    companion object {
        private const val FEED_IDEA_TITLE = "title"
        private const val FEED_IDEA_TAGS = "tags"

        fun createIntent(context: Context, title : String, tags : String) : Intent {
            val intent : Intent = Intent(context, FeedItemDetailActivity::class.java)
            intent.putExtra(FEED_IDEA_TITLE, title)
            intent.putExtra(FEED_IDEA_TAGS, tags)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_item_detail)

        title = intent.getStringExtra(FEED_IDEA_TITLE)!!
        tags = intent.getStringExtra(FEED_IDEA_TAGS)!!

        val titleTestView : TextView = findViewById<Button>(R.id.feed_item_detail_textView_title)
        titleTestView.text = title
        val tagsTestView : TextView = findViewById<Button>(R.id.feed_item_detail_textView_tags)
        tagsTestView.text = tags

    }
}
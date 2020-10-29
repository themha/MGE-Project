package com.ost.rj.mge.testat.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.model.IdeaRepository
import com.ost.rj.mge.testat.model.storage.network.RetrofitBuilder

class FeedItemDetailActivity : AppCompatActivity() {

    private lateinit var id : String
    private lateinit var title : String
    private lateinit var tags : String
    private lateinit var description : String
    private var likes : Int = 0;


    companion object {
        private const val FEED_IDEA_ID = "id"
        private const val FEED_IDEA_TITLE = "title"
        private const val FEED_IDEA_TAGS = "tags"
        private const val FEED_IDEA_DESCRIPTION = "description"
        private const val FEED_IDEA_LIKES = "likes"

        fun createIntent(context: Context, id: String?, title : String?, tags : String?, description: String?, likes: Int) : Intent {
            val intent : Intent = Intent(context, FeedItemDetailActivity::class.java)
            intent.putExtra(FEED_IDEA_ID, id)
            intent.putExtra(FEED_IDEA_TITLE, title)
            intent.putExtra(FEED_IDEA_TAGS, tags)
            intent.putExtra(FEED_IDEA_DESCRIPTION, description)
            intent.putExtra(FEED_IDEA_LIKES, likes)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_item_detail)

        id = intent.getStringExtra(FEED_IDEA_ID)!!
        title = intent.getStringExtra(FEED_IDEA_TITLE)!!
        tags = intent.getStringExtra(FEED_IDEA_TAGS)!!
        description = intent.getStringExtra(FEED_IDEA_DESCRIPTION)!!
        likes = intent.getIntExtra(FEED_IDEA_LIKES, 0)

        val titleTestView: TextView = findViewById<Button>(R.id.feed_item_detail_textView_title)
        titleTestView.text = title
        val tagsTestView: TextView = findViewById<Button>(R.id.feed_item_detail_textView_tags)
        tagsTestView.text = tags
        val descriptionTestView: TextView = findViewById<Button>(R.id.feed_item_detail_textView_description)
        descriptionTestView.text = description
        val likesTestView : TextView = findViewById<TextView>(R.id.feed_item_detail_textView_likes)
        likesTestView.text = likes.toString();

        val buttonUpvote : ImageButton = findViewById<ImageButton>(R.id.feed_item_detail_imageButton_voteUp)
        val buttonDownvote : ImageButton = findViewById<ImageButton>(R.id.feed_item_detail_imageButton_voteDown)

        buttonUpvote.setOnClickListener {
            IdeaRepository.addSupporterToIdea(id, ++likes)
            //TODO cheat
            likesTestView.text = likes.toString()
        }


        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }
}
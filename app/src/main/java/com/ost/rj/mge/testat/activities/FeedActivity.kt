package com.ost.rj.mge.testat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.adapter.IdeaAdapter
import com.ost.rj.mge.testat.adapter.IdeaViewHolder
import com.ost.rj.mge.testat.model.Idea
import com.ost.rj.mge.testat.model.IdeaRepository

class FeedActivity : AppCompatActivity() {
    private lateinit var adapter : RecyclerView.Adapter<IdeaViewHolder>

    companion object {

        fun createIntent(context: Context) : Intent {
            val intent : Intent = Intent(context, FeedActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        adapter = IdeaAdapter(IdeaRepository.getIdeas(), ::onItemClick)


        val recyclerView : RecyclerView = findViewById(R.id.feed)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val fabButtonAdd: View = findViewById(R.id.feed_fab_add_idea)
        fabButtonAdd.setOnClickListener {
            buildIdeaFormActivity()
            /*
            view -> Snackbar.make(view, "Start activity for adding new ideas", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()

             */
        }

    }

    private fun onItemClick(idea : Idea) {
        buildFeedDetailActivity(idea.title, idea.tags)
    }

    private fun buildFeedDetailActivity(title: String, tags: String) {
        val feedActivityIntent : Intent = FeedItemDetailActivity.createIntent(this, title, tags)
        startActivity(feedActivityIntent)
    }

    private fun buildIdeaFormActivity(){
        val ideaFormActivity : Intent = IdeaFormActivity.createIntent(this)
        startActivity(ideaFormActivity)
    }
}
package com.ost.rj.mge.testat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.adapter.IdeaAdapter
import com.ost.rj.mge.testat.adapter.IdeaViewHolder
import com.ost.rj.mge.testat.adapter.OnItemClickListener
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


    }

    private fun onItemClick(idea : Idea) {
        buildFeedDetailActivity(idea.title, idea.tags)
    }

    private fun buildFeedDetailActivity(title: String, tags: String) {
        val feedActivityIntent : Intent = FeedDetailActivity.createIntent(this, title, tags)
        startActivity(feedActivityIntent)
    }
}
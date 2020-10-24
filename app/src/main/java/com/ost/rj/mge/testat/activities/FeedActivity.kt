package com.ost.rj.mge.testat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.adapter.IdeaAdapter
import com.ost.rj.mge.testat.model.Idea
import com.ost.rj.mge.testat.model.IdeaRepository

class FeedActivity : AppCompatActivity() {
    private lateinit var adapter : IdeaAdapter

    companion object {

        fun createIntent(context: Context) : Intent {
            return Intent(context, FeedActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)

        IdeaRepository.syncDatabase()

        val swipeRefreshLayout : SwipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.feed_swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener {


            IdeaRepository.syncDatabase()
            swipeRefreshLayout.isRefreshing = false
            Toast.makeText(this, "Feed successfully synced", Toast.LENGTH_SHORT).show()

            /*
            Log.d("test", "refresh")
            IdeaRepository.syncDatabase {
                Toast.makeText(this, "Feed successfully synced", Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
                Log.d("test", "test3")
            }

             */


        }

        //todo nicht ganz schön
        adapter = IdeaAdapter(::onItemClick)

        IdeaRepository.getIdeas().observe(this, Observer {ideas ->
            adapter.updateData(ideas)
        })


        val recyclerView : RecyclerView = findViewById(R.id.feed)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val fabButtonAdd: View = findViewById(R.id.feed_fab_add_idea)
        fabButtonAdd.setOnClickListener {
            buildIdeaFormActivity()

        }

    }


    override fun onResume(){
        super.onResume()

        IdeaRepository.syncDatabase()
    }


    private fun onItemClick(idea : Idea) {
        buildFeedDetailActivity(idea.title, idea.tags, idea.description, idea.likes)
    }

    private fun buildFeedDetailActivity(title: String?, tags: String?, description: String?, likes: Int) {
        val feedActivityIntent : Intent = FeedItemDetailActivity.createIntent(this, title, tags, description, likes)
        startActivity(feedActivityIntent)
    }

    private fun buildIdeaFormActivity(){
        val ideaFormActivity : Intent = IdeaFormActivity.createIntent(this)
        startActivity(ideaFormActivity)
    }
}
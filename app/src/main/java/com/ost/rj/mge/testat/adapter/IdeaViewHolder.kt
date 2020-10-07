package com.ost.rj.mge.testat.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ost.rj.mge.testat.activities.FeedDetailActivity
import com.ost.rj.mge.testat.model.Idea
import com.ost.rj.mge.testat.model.IdeaRepository


class IdeaViewHolder(
    parent: View,
    var title: TextView,
    var tags: TextView
) : RecyclerView.ViewHolder(parent) {

    init {
        parent.setOnClickListener{ v: View ->

            if (position != RecyclerView.NO_POSITION){
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You clicked on itme # ${position + 1}",
                    Toast.LENGTH_SHORT
                ).show()

                val idea : Idea = IdeaRepository.getIdeas()[position]

                buildFeedDetailActivity(parent.context, idea.title, idea.tags)
            }
        }
    }

    private fun buildFeedDetailActivity(context : Context, title: String, tags: String) {
        val feedActivityIntent : Intent = FeedDetailActivity.createIntent(context, title, tags)
        context.startActivity(feedActivityIntent)
    }

    /*
    override fun onClick(v: View?) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION){
            listener.onItemClick(position)
        }

        //TODO("Not yet implemented")
    }

     */
}
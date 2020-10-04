package com.ost.rj.mge.testat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.model.Idea

class IdeaAdapter(ideas: ArrayList<Idea>): RecyclerView.Adapter<IdeaViewHolder>() {
    private val ideas: ArrayList<Idea> = ideas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaViewHolder {
        val context : Context = parent.context
        val inflater : LayoutInflater = LayoutInflater.from(context)

        val view : View = inflater.inflate(
            R.layout.activity_feed_item,
            parent,
            false
        )

        val titleTextView : TextView = view.findViewById(R.id.feed_item_text_title)
        val tagsTextView : TextView = view.findViewById(R.id.feed_item_text_tags)

        return IdeaViewHolder(view, titleTextView, tagsTextView)
    }

    override fun onBindViewHolder(holder: IdeaViewHolder, position: Int) {
        val idea : Idea = this.ideas[position]
        holder.title.text = idea.title
        holder.tags.text = idea.tags

    }

    override fun getItemCount(): Int {
        return this.ideas.size

    }

}
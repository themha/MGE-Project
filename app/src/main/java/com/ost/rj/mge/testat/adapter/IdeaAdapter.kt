package com.ost.rj.mge.testat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.model.Idea
import com.ost.rj.mge.testat.model.IdeaRepository

class IdeaAdapter(
    private var ideas: List<Idea>,
    private val clickListener: (idea: Idea) -> Unit

): RecyclerView.Adapter<IdeaViewHolder>() {

    fun updateIdeas(ideas: List<Idea>){
        this.ideas = ideas
        this.notifyDataSetChanged()
    }

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

        holder.itemView.setOnClickListener {v: View ->
            if (position != RecyclerView.NO_POSITION){

                val idea : Idea = IdeaRepository.getIdeas()[position]
                clickListener(idea)

            }
        }
    }

    override fun getItemCount(): Int {
        return this.ideas.size
    }

}
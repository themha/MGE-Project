package com.ost.rj.mge.testat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.model.Idea

class IdeaAdapter(
    //private var ideas: List<Idea>,
    private val clickListener: (idea: Idea) -> Unit

): RecyclerView.Adapter<IdeaViewHolder>() {
//Todo nicht ganz schön
    private var ideas: List<Idea> = emptyList()

    fun updateData(ideas: List<Idea>){
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

                val idea : Idea = ideas[position]
                clickListener(idea)

            }
        }
    }

    override fun getItemCount(): Int {
        return this.ideas.size
    }

}

/*
class IdeaDiffCallback : DiffUtil.ItemCallback<Idea>() {
    override fun areItemsTheSame(oldItem: Idea, newItem: Idea): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Idea, newItem: Idea): Boolean {
        return oldItem == newItem
    }

}
*/
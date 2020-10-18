package com.ost.rj.mge.testat.model

import android.content.Context
import androidx.room.Room
import com.ost.rj.mge.testat.model.storage.IdeaDatabase


object IdeaRepository {
    private lateinit var database: IdeaDatabase

    fun initialize(context: Context) {
        database = Room.databaseBuilder(
            context,
            IdeaDatabase::class.java, "idea.db"
        ).allowMainThreadQueries().build()

        if(getIdeas().isEmpty()){
            addIdea("Jetski Verleihung", "#event #adventure #experience", "Very Cool")
            addIdea("Facebook 2.0", "#socialNetwork", "Give us your Money")
        }

    }

    fun getIdeas(): List<Idea> {
        return database.ideaDao().getIdeas()
    }

    private fun addIdea(idea: Idea) {
        database.ideaDao().insert(idea)
    }

    fun addIdea(title: String, tags: String, description: String): Idea {
        val idea = Idea(title, tags, description)

        addIdea(idea)

        return idea
    }
}
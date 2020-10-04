package com.ost.rj.mge.testat.model

object IdeaRepository {
    private var ideas : ArrayList<Idea> = ArrayList()

    init {
        ideas = ArrayList<Idea>()

        ideas.add(Idea("Jetski Verleihung", "#event #adventure #experience"))
        ideas.add(Idea("Facebook 2.0", "#socialNetwork"))
    }

    fun getIdeas() : ArrayList<Idea> {
        return ideas
    }

    fun addIdea(idea: Idea) {
        ideas.add(idea)
    }

}
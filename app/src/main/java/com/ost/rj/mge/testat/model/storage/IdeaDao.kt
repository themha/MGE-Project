package com.ost.rj.mge.testat.model.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ost.rj.mge.testat.model.Idea;

@Dao
interface IdeaDao {
    @Query("SELECT * FROM ideas")
    fun getIdeas(): List<Idea>

    @Insert
    fun insert(idea: Idea)
}
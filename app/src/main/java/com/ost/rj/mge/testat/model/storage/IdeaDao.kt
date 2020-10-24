package com.ost.rj.mge.testat.model.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ost.rj.mge.testat.model.Idea;

@Dao
interface IdeaDao {

    @Query("SELECT * FROM Ideas")
    fun getIdeas(): LiveData<List<Idea>>

    @Update
    suspend fun update(idea: Idea)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(idea: Idea)

    @Delete
    suspend fun delete(idea: Idea)

    @Query("DELETE FROM Ideas")
    fun eraseDb()
}
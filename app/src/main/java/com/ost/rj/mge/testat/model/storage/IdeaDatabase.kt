package com.ost.rj.mge.testat.model.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.ost.rj.mge.testat.model.Idea

@Database(entities = [Idea::class], version = 1, exportSchema = false)
abstract class IdeaDatabase : RoomDatabase() {
    abstract fun ideaDao(): IdeaDao

}


package com.ost.rj.mge.testat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ideas")
public final class Idea {
    @PrimaryKey(autoGenerate = true)
    var id = 1

    @ColumnInfo
    var title: String? = null

    @ColumnInfo
    var tags: String? = null

    @ColumnInfo
    var description: String? = null
}
package com.ost.rj.mge.testat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ideas")
data class Idea (
    @ColumnInfo
    val title: String,

    @ColumnInfo
    val tags: String,

    @ColumnInfo
    val description: String,

    @ColumnInfo
    val author: String,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
){
    @ColumnInfo
    var likes: Int = 0
}
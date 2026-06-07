package com.ahmadmaaz1.newsy.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Search", indices = [Index(value = ["search"], unique = true)])
data class SearchModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val search: String,
    val timeStamp: Long = System.currentTimeMillis()

)

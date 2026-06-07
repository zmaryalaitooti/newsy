package com.ahmadmaaz1.newsy.data.local.dao

import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmadmaaz1.newsy.domain.model.SearchModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetSuggestion(value: SearchModel)

    @Delete
    suspend fun deleteSuggestion(value: SearchModel)

    @Query("SELECT COUNT(*) FROM Search")
    suspend fun getSearchCount(): Int

    @Query("Select * from Search ORDER BY timeStamp DESC")
    fun selectSuggestions (): Flow<List<SearchModel>>

    @Query("SELECT * FROM Search WHERE id = :id")
    suspend fun selectSuggestionById(id : Long) : SearchModel
}
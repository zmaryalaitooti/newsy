package com.ahmadmaaz1.newsy.dbtest

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ahmadmaaz1.newsy.data.local.NewsDb
import com.ahmadmaaz1.newsy.data.local.NewsTypeConverter
import com.ahmadmaaz1.newsy.data.local.dao.SearchDao
import com.ahmadmaaz1.newsy.domain.model.SearchModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchDaoTest {

    private lateinit var database: NewsDb
    private lateinit var dao: SearchDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDb::class.java
        )
            .addTypeConverter(NewsTypeConverter())
            .allowMainThreadQueries()
            .build()

        dao = database.getSearchDao()
    }

    @After
    fun teardown() {
        if (::database.isInitialized) {
            database.close()
        }    }

    @Test
    fun insertSuggestion_insertsDataSuccessfully() = runTest {

        val search = SearchModel(
            search = "Home"
        )

        dao.insetSuggestion(search)

        val result = dao.selectSuggestions().first()

        assertEquals(1, result.size)
        assertEquals("Home", result.first().search)
    }

    @Test
    fun insertSuggestion_ignoreDuplicateSearch() = runTest {

        val first = SearchModel(search = "Kotlin")
        val second = SearchModel(search = "Kotlin")

        dao.insetSuggestion(first)
        dao.insetSuggestion(second)

        val result = dao.selectSuggestions().first()

        assertEquals(1, result.size)
    }

    @Test
    fun deleteSuggestion_deletesDataSuccessfully() = runTest {

        val search = SearchModel(search = "Jetpack Compose")

        dao.insetSuggestion(search)

        val inserted = dao.selectSuggestions().first().first()

        dao.deleteSuggestion(inserted)

        val result = dao.selectSuggestions().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun selectSuggestionById_returnsCorrectItem() = runTest {

        val search = SearchModel(search = "Room Database")

        dao.insetSuggestion(search)

        val inserted = dao.selectSuggestions().first().first()

        val result = dao.selectSuggestionById(inserted.id)

        assertEquals(inserted.id, result.id)
        assertEquals("Room Database", result.search)
    }
}
package com.ahmadmaaz1.newsy.flow

import app.cash.turbine.test
import com.ahmadmaaz1.newsy.presentatoin.search.Person
import com.ahmadmaaz1.newsy.presentatoin.search.PersonState
import com.ahmadmaaz1.newsy.presentatoin.search.TestViewmodel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions

class FlowWithViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    val dis = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `flow test with viewmodel example onSuccess`() = runTest {
        val view = TestViewmodel(person = MemoryPerson(), dis, dis)

        view.uiState.test {
            Assertions.assertEquals(PersonState.Loading,awaitItem())
            advanceTimeBy(100)
            Assertions.assertEquals(PersonState.Success(listOf("h", "khan", "jan")),awaitItem())
        }
    }
}

class MemoryPerson() : Person {
    override fun getPerson(): Flow<List<String>> {
        return flow {
            delay(100)
            emit(listOf("ali", "khan", "jan"))
        }
    }

}
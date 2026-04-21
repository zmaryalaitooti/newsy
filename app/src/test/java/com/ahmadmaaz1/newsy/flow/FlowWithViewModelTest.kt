package com.ahmadmaaz1.newsy.flow//package com.ahmadmaaz1.newsy.flow
//
//import app.cash.turbine.test
//import com.ahmadmaaz1.newsy.presentatoin.search.Person
//import com.ahmadmaaz1.newsy.presentatoin.search.PersonState
//import com.ahmadmaaz1.newsy.presentatoin.search.TestViewmodel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.test.TestDispatcher
//import kotlinx.coroutines.test.UnconfinedTestDispatcher
//import kotlinx.coroutines.test.advanceTimeBy
//import kotlinx.coroutines.test.runTest
//import org.junit.Test
//import org.junit.Assert.assertEquals
//class FlowWithViewModelTest {
//    @OptIn(ExperimentalCoroutinesApi::class)
//    val dis = UnconfinedTestDispatcher()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun flowTestWithViewModelExample_onSuccess() = runTest {
//        val view = TestViewmodel(person = MemoryPerson(), dis, dis)
//
//        view.uiState.test {
//            assertEquals(PersonState.Success(listOf("l", "khan", "jan")),awaitItem())
//        }
//    }
//}
//
//class MemoryPerson() : Person {
//    override fun getPerson(): Flow<List<String>> {
//        return flow {
//            delay(100)
//            emit(listOf("ali", "khan", "jan"))
//        }
//    }
//
//}



import app.cash.turbine.test
import com.ahmadmaaz1.newsy.presentatoin.search.Person
import com.ahmadmaaz1.newsy.presentatoin.search.PersonState
import com.ahmadmaaz1.newsy.presentatoin.search.TestViewmodel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FlowWithViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    val dis = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun flowTestWithViewModelExample_onSuccess() = runTest {
        val view = TestViewmodel(person = MemoryPerson(), dis, dis)

        view.uiState.test {
            assertEquals(PersonState.Loading, awaitItem())
            assertEquals(PersonState.Success(listOf("h", "khan", "jan")), awaitItem())
            awaitComplete()
        }
    }
}

class MemoryPerson : Person {
    override fun getPerson(): Flow<List<String>> = flow {
        delay(100)
        emit(listOf("ali", "khan", "jan"))
    }
}
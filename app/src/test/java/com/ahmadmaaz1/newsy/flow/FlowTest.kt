package com.ahmadmaaz1.newsy.flow

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FlowTest {

    @Test
    fun testFlow() = runTest {
        val result = numberFlow().toList()
        Assertions.assertEquals(listOf(1,0,3), result)
    }

    @Test
     fun tesStr() = runTest {
         var strRes = mutableListOf<String>()
        strFlow().collect {
            strRes.add(it)
        }
        Assertions.assertEquals(listOf("str48","str49","str50"),strRes)
    }

//    @Test
//    fun testViewmodel() = runTest {
//        val viewmodel = TestViewmodel()
//        viewmodel.incremental()
//
//        Assertions.assertEquals(1,viewmodel.counter.value)
//    }


    @Test
    fun testWithTurbin() = runTest{
        numberFlow().test {
            assertEquals(1,awaitItem())
            assertEquals(0,awaitItem())
            assertEquals(3,awaitItem())
            awaitComplete()
        }
    }

//    @Test
//    fun testRepo() = runTest {
//        val repo = Repo()
//
//        repo.getData().test {
//            assertEquals("Hello",awaitItem())
//            awaitComplete()
//        }
//    }
}


fun numberFlow(): Flow<Int> = flow {
    emit(1)
    emit(0)
    emit(3)
}
fun strFlow():Flow<String> = flow {
    for (i in 48..50){
        emit("str$i")
    }
}
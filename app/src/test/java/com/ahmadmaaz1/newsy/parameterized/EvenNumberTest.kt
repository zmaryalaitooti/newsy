package com.ahmadmaaz1.newsy.parameterized

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EvenNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1,3,5,7])
    fun testEvenNumbers(number: Int) {
        assertTrue(number % 2 == 1)
    }

    @ParameterizedTest
    @ValueSource(strings = ["khan","asad"])
    fun testName(name: String){
        val isName = name.length >= 4
        assertTrue(isName)
    }
}
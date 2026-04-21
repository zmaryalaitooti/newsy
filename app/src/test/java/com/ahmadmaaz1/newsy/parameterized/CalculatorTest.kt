package com.ahmadmaaz1.newsy.parameterized

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class CalculatorTest {

    @ParameterizedTest
    @CsvSource(
        "2, 3, 5",
        "10, 5, 15",
        "3, 7, 10"
    )
    fun testAddition(a: Int, b: Int, expected: Int) {
        assertEquals(expected, a + b)
    }


    @ParameterizedTest
    @CsvSource("2,4,8","3,3,9","3,4,12")
    fun testMulti(a: Int, b: Int, result: Int){
        assertEquals(a*b,result)
    }

    @Test
    fun testAddition() {
        val result = 2 + 3
        assertEquals(5, result)
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    fun numbersTest(a: Int,b: Int,expected: Int){
        assertEquals(expected,a+b)
    }

companion object {
        @JvmStatic
        fun provideNumbers() = listOf(
            Arguments.of(1, 2, 3),
            Arguments.of(5, 5, 10),
            Arguments.of(3, 4, 7)
        )
    }
}


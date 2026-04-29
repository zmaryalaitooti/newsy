package com.ahmadmaaz1.newsy.parameterized

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class UsernameTest {

    @ParameterizedTest
    @ValueSource(strings = ["Ali1"])
    fun validUsernames(username: String) {
        assertTrue(isValidUsername(username))
    }

    @ParameterizedTest
    @ValueSource(strings = ["Ali", "A", "Ab"])
    fun invalidUsernames(username: String) {
        assertFalse(isValidUsername(username))
    }

    fun isValidUsername(username: String): Boolean {
        return username.length >= 5
    }
}

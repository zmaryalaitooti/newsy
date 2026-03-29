package com.ahmadmaaz1.newsy.presentatoin.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TestViewmodel constructor(
    val person: Person, val mainDispatcher: CoroutineDispatcher,
    val testDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<PersonState>(PersonState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getPerson()
    }

    private fun getPerson() {
        viewModelScope.launch(mainDispatcher) {
            _uiState.value = PersonState.Loading
            person.getPerson().catch {
                _uiState.value = PersonState.Failure("Some error occur ")
            }.collect {
                _uiState.value = PersonState.Success(it)
            }
        }
    }
}

interface Person {
    fun getPerson(): Flow<List<String>>
}

sealed class PersonState {
    data class Success(val listOfPerson: List<String>) : PersonState()
    object Loading : PersonState()
    class Failure(val message: String) : PersonState()

}
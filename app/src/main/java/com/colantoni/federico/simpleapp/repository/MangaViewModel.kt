package com.colantoni.federico.simpleapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MangaViewModel : ViewModel() {
    private val repository: MangaRepository = MangaRepository()

    val firstTodo = liveData(Dispatchers.IO) {
        val retrivedTodo = repository.getManga(1)

        emit(retrivedTodo)
    }
}
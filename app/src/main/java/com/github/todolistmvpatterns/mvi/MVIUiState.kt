package com.github.todolistmvpatterns.mvi

import com.github.todolistmvpatterns.data.Task

data class MVIUiState(
    val tasks: List<Task>,
    val inputedText: String,
    val createButtonEnabled: Boolean,
)


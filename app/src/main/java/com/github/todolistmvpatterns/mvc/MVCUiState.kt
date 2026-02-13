package com.github.todolistmvpatterns.mvc

import com.github.todolistmvpatterns.data.Task

data class MVCUiState(
    val tasks: List<Task>,
    val inputedText: String,
    val createButtonEnabled: Boolean,
)


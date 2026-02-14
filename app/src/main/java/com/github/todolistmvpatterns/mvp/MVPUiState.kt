package com.github.todolistmvpatterns.mvp

import com.github.todolistmvpatterns.data.Task

data class MVPUiState(
    val tasks: List<Task>,
    val inputedText: String,
    val createButtonEnabled: Boolean,
)


package com.github.todolistmvpatterns.mvvm_state

import com.github.todolistmvpatterns.data.Task

data class MVVMUiState(
    val tasks: List<Task>,
    val inputedText: String,
    val createButtonEnabled: Boolean,
)


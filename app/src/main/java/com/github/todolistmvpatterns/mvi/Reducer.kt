package com.github.todolistmvpatterns.mvi

import com.github.todolistmvpatterns.data.Task

object TodoListReducer {

    fun MVIUiState.inputChanged(text: String): MVIUiState =
        copy(
            inputedText = text,
            createButtonEnabled = text.isNotBlank(),
        )

    fun MVIUiState.taskAdded(): MVIUiState =
        copy(
            inputedText = "",
            createButtonEnabled = false,
        )

    fun MVIUiState.tasksLoaded(tasks: List<Task>): MVIUiState =
        copy(tasks = tasks)

}
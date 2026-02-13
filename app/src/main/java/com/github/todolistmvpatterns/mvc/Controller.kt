package com.github.todolistmvpatterns.mvc

import com.github.todolistmvpatterns.data.Repository
import com.github.todolistmvpatterns.data.Task

class Controller(
    private val view: MVCView,
    private val repository: Repository,
) {
    private var state: MVCUiState = MVCUiState(
        inputedText = "",
        tasks = readTasks(),
        createButtonEnabled = false,
    )

    fun start() = view.render(state)
    fun readTasks(): List<Task> {
        return repository.readTasks()
    }

    fun onInputChanged(text: String) {
        state = state.copy(
            inputedText = text,
            createButtonEnabled = text.isNotBlank(),
        )
        view.render(state)
    }

    fun onAddTaskClicked() {
        repository.addTask(state.inputedText)
        state = state.copy(tasks = readTasks(), inputedText = "", createButtonEnabled = false)
        view.render(state)
    }

    fun onToggleClicked(id: Long) {
        repository.taskStateChanged(id)
        state = state.copy(tasks = readTasks())
        view.render(state)
    }

    fun onDeleteTaskClicked(id: Long) {
        repository.deleteTask(id)
        state = state.copy(tasks = readTasks())
        view.render(state)
    }
}
package com.github.todolistmvpatterns.mvi

import com.github.todolistmvpatterns.data.Task


sealed interface Intent {
    data class InputChanged(val text: String) : Intent
    data object AddClicked : Intent
    data class ToggleClicked(val id: Long) : Intent
    data class DeleteClicked(val id: Long) : Intent

    data class TasksChanged(val tasks: List<Task>) : Intent
}
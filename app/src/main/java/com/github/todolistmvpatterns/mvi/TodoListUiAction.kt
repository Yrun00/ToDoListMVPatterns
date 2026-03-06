package com.github.todolistmvpatterns.mvi


sealed interface TodoListUiAction {
    data class InputChanged(val text: String) : TodoListUiAction
    data object AddClicked : TodoListUiAction
    data class ToggleClicked(val id: Long) : TodoListUiAction
    data class DeleteClicked(val id: Long) : TodoListUiAction
}
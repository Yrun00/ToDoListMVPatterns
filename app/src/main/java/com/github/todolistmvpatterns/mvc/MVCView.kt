package com.github.todolistmvpatterns.mvc

interface MVCView {
    fun render(state: MVCUiState)
}
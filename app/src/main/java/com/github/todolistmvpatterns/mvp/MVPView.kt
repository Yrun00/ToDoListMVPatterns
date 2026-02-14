package com.github.todolistmvpatterns.mvp

interface MVPView {
    fun render(state: MVPUiState)
}
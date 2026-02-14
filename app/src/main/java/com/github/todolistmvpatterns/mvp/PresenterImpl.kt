package com.github.todolistmvpatterns.mvp

import com.github.todolistmvpatterns.data.Repository
import com.github.todolistmvpatterns.data.Task
import javax.inject.Inject

class PresenterImpl @Inject constructor(
    private val repository: Repository,
) : Presenter {

    private var view: MVPView? = null
    private var state: MVPUiState = MVPUiState(
        inputedText = "",
        tasks = readTasks(),
        createButtonEnabled = false,
    )

    override fun start(): Unit {
        view?.render(state)
    }

    fun readTasks(): List<Task> {
        return repository.readTasks()
    }

    override fun onInputChanged(text: String) {
        state = state.copy(
            inputedText = text,
            createButtonEnabled = text.isNotBlank(),
        )
        view?.render(state)
    }

    override fun onAddTaskClicked() {
        repository.addTask(state.inputedText)
        state = state.copy(tasks = readTasks(), inputedText = "", createButtonEnabled = false)
        view?.render(state)
    }

    override fun onToggleClicked(id: Long) {
        repository.taskStateChanged(id)
        state = state.copy(tasks = readTasks())
        view?.render(state)
    }

    override fun onDeleteTaskClicked(id: Long) {
        repository.deleteTask(id)
        state = state.copy(tasks = readTasks())
        view?.render(state)
    }

    override fun attach(view: MVPView) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}
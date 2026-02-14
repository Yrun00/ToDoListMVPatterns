package com.github.todolistmvpatterns.mvp

interface Presenter {

    fun attach(view: MVPView)

    fun detach();
    fun onInputChanged(text: String)
    fun start():Unit?
    fun onAddTaskClicked()
    fun onToggleClicked(id: Long)
    fun onDeleteTaskClicked(id: Long)
}
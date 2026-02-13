package com.github.todolistmvpatterns.data

interface Repository {

    fun readTasks(): List<Task>

    fun addTask(taskTitle: String)

    fun deleteTask(taskId:Long)

    fun taskStateChanged(taskId:Long)
}
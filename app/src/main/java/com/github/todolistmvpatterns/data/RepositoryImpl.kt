package com.github.todolistmvpatterns.data

import javax.inject.Inject

import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val taskDao: TaskDao) : Repository {

    override fun readTasks(): List<Task> {
        return taskDao.readTasks()
    }

    override fun addTask(taskTitle: String) {
        taskDao.insert(
            Task(
                title = taskTitle,
                done = false,
            ),
        )
    }

    override fun deleteTask(taskId: Long) {
        taskDao.delete(taskId)
    }

    override fun taskStateChanged(taskId: Long) {
        taskDao.toggleDone(taskId)
    }
}
package com.github.todolistmvpatterns.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val taskDao: TaskDao) : Repository {

    override fun observeTasks(): Flow<List<Task>> {
        return taskDao.observeTasks()
    }

    override suspend fun addTask(taskTitle: String) {
        withContext(Dispatchers.IO) {
            taskDao.insert(
                Task(
                    title = taskTitle,
                    done = false,
                ),
            )
        }
    }

    override suspend fun deleteTask(taskId: Long) {
        withContext(Dispatchers.IO) {
            taskDao.delete(taskId)
        }
    }

    override suspend fun taskStateChanged(taskId: Long) {
        withContext(Dispatchers.IO) {
            taskDao.toggleDone(taskId)
        }
    }
}
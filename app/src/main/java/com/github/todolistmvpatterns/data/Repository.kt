package com.github.todolistmvpatterns.data

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun observeTasks(): Flow<List<Task>>

    suspend fun addTask(taskTitle: String)

    suspend fun deleteTask(taskId: Long)

    suspend fun taskStateChanged(taskId: Long)
}
package com.github.todolistmvpatterns.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun observeTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Query("DELETE FROM task_table WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("UPDATE task_table SET done = NOT done WHERE id = :taskId")
    suspend fun toggleDone(taskId: Long)
}
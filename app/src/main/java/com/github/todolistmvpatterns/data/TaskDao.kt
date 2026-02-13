package com.github.todolistmvpatterns.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table")
    fun readTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task): Long

    @Query("DELETE FROM task_table WHERE id = :id")
    fun delete(id: Long)

    @Query("UPDATE task_table SET done = NOT done WHERE id = :taskId")
    fun toggleDone(taskId: Long): Unit
}
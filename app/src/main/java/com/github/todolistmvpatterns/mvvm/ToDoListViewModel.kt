package com.github.todolistmvpatterns.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.todolistmvpatterns.data.Repository
import com.github.todolistmvpatterns.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _input = MutableStateFlow("")
    val input: StateFlow<String> = _input

    val createEnabled: StateFlow<Boolean> =
        _input.map { it.isNotBlank() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val tasks: StateFlow<List<Task>> =
        repository.observeTasks()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onInputChanged(text: String) {
        _input.value = text
    }

    fun onAddClicked() = viewModelScope.launch {
        repository.addTask(_input.value)
        _input.value = ""
    }

    fun onToggle(id: Long) = viewModelScope.launch { repository.taskStateChanged(id) }

    fun onDelete(id: Long) = viewModelScope.launch { repository.deleteTask(id) }
}
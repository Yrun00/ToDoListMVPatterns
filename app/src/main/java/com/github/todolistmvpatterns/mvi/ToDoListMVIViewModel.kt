package com.github.todolistmvpatterns.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.todolistmvpatterns.data.Repository
import com.github.todolistmvpatterns.mvi.TodoListReducer.inputChanged
import com.github.todolistmvpatterns.mvi.TodoListReducer.taskAdded
import com.github.todolistmvpatterns.mvi.TodoListReducer.tasksLoaded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListMVIViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow(
        MVIUiState(inputedText = "", tasks = emptyList(), createButtonEnabled = false),
    )
    val uiState: StateFlow<MVIUiState> = _state.asStateFlow()

    init {
        repository.observeTasks()
            .onEach { tasks -> _state.update { it.tasksLoaded(tasks) } }
            .launchIn(viewModelScope)
    }

    fun dispatch(action: TodoListUiAction) {
        when (action) {
            is TodoListUiAction.InputChanged -> {
                _state.update { it.inputChanged(action.text) }

            }

            TodoListUiAction.AddClicked -> {
                val title = _state.value.inputedText
                if (title.isEmpty()) return
                _state.update { it.taskAdded() }
                viewModelScope.launch { repository.addTask(title) }
            }

            is TodoListUiAction.ToggleClicked -> {
                viewModelScope.launch { repository.taskStateChanged(action.id) }
            }

            is TodoListUiAction.DeleteClicked -> {
                viewModelScope.launch { repository.deleteTask(action.id) }
            }
        }
    }
}

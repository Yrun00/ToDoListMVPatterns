package com.github.todolistmvpatterns.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.todolistmvpatterns.data.Repository
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
        MVIUiState(
            inputedText = "",
            tasks = emptyList(),
            createButtonEnabled = false,
        ),
    )
    val uiState: StateFlow<MVIUiState> = _state.asStateFlow()

    init {
        repository.observeTasks()
            .onEach { tasks -> dispatch(Intent.TasksChanged(tasks)) }
            .launchIn(viewModelScope)
    }

    fun dispatch(intent: Intent) {
        when (intent) {
            is Intent.InputChanged -> {
                reduce(intent)
            }

            Intent.AddClicked -> {
                val title = _state.value.inputedText
                if (title.isEmpty()) return

                _state.update { it.copy(inputedText = "", createButtonEnabled = false) }
                viewModelScope.launch {
                    repository.addTask(title)
                }
            }

            is Intent.ToggleClicked -> {
                viewModelScope.launch {
                    repository.taskStateChanged(intent.id)
                }
            }

            is Intent.DeleteClicked -> {
                viewModelScope.launch {
                    repository.deleteTask(intent.id)
                }
            }

            is Intent.TasksChanged -> {
                reduce(intent)
            }
        }
    }

    private fun reduce(intent: Intent) {
        _state.update { old ->
            when (intent) {
                is Intent.InputChanged -> {
                    old.copy(
                        inputedText = intent.text,
                        createButtonEnabled = intent.text.isNotBlank(),
                    )
                }

                is Intent.TasksChanged -> {
                    old.copy(tasks = intent.tasks)
                }

                else -> old
            }
        }
    }
}
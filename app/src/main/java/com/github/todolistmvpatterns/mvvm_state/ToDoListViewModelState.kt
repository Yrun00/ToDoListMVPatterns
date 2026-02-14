package com.github.todolistmvpatterns.mvvm_state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.todolistmvpatterns.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModelState @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val input = MutableStateFlow("")

    val uiState: StateFlow<MVVMUiState> =
        combine(
            input,
            repository.observeTasks(), // Flow<List<Task>>
        ) { inputText, tasks ->
            MVVMUiState(
                tasks = tasks,
                inputedText = inputText,
                createButtonEnabled = inputText.isNotBlank(),
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MVVMUiState(
                inputedText = "",
                tasks = emptyList(),
                createButtonEnabled = false,
            ),
        )

    fun onInputChanged(text: String) {
        input.value = text
    }

    fun onAddClicked() = viewModelScope.launch {
        repository.addTask(input.value)
        input.value = ""
    }

    fun onToggle(id: Long) = viewModelScope.launch { repository.taskStateChanged(id) }

    fun onDelete(id: Long) = viewModelScope.launch { repository.deleteTask(id) }
}
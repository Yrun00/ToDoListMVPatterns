package com.github.todolistmvpatterns.mvvm_state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.todolistmvpatterns.ToDoListView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MVVMStateMainActivity : ComponentActivity() {

    private val viewModel: ToDoListViewModelWithState by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            ToDoListView(
                inputText = state.inputedText,
                tasks = state.tasks,
                createButtonEnabled = state.createButtonEnabled,
                onInputChange = { text -> viewModel.onInputChanged(text) },
                onAddTask = { viewModel.onAddClicked() },
                onToggle = { id -> viewModel.onToggle(id) },
                onDeleteTask = { id -> viewModel.onDelete(id) },
                modifier = Modifier,
            )
        }
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onResume() {
        super.onResume()

    }
}


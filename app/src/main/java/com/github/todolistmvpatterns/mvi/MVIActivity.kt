package com.github.todolistmvpatterns.mvi

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
class MVIMainActivity : ComponentActivity() {

    private val viewModel: ToDoListMVIViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            ToDoListView(
                inputText = state.inputedText,
                tasks = state.tasks,
                createButtonEnabled = state.createButtonEnabled,
                onInputChange = { text -> viewModel.dispatch(Intent.InputChanged(text)) },
                onAddTask = { viewModel.dispatch(Intent.AddClicked) },
                onToggle = { id -> viewModel.dispatch(Intent.ToggleClicked(id)) },
                onDeleteTask = { id -> viewModel.dispatch(Intent.DeleteClicked(id)) },
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


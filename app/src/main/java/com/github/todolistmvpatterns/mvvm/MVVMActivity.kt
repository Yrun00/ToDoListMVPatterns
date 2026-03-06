package com.github.todolistmvpatterns.mvvm

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
class MVVMMainActivity : ComponentActivity() {

    private val viewModel: ToDoListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val tasks by viewModel.tasks.collectAsStateWithLifecycle()
            val input by viewModel.input.collectAsStateWithLifecycle()
            val enabled by viewModel.createEnabled.collectAsStateWithLifecycle()
            ToDoListView(
                inputText = input,
                tasks = tasks,
                createButtonEnabled = enabled,
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


package com.github.todolistmvpatterns.mvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.github.todolistmvpatterns.ToDoListView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MVPMainActivity : ComponentActivity(), MVPView {

    @Inject
    lateinit var presenter: Presenter
    private var uiState by mutableStateOf(
        MVPUiState(
            inputedText = "",
            tasks = emptyList(),
            createButtonEnabled = false,
        ),
    )

    override fun render(state: MVPUiState) {
        uiState = state
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListView(
                inputText = uiState.inputedText,
                tasks = uiState.tasks,
                createButtonEnabled = uiState.createButtonEnabled,
                onInputChange = { text -> presenter.onInputChanged(text) },
                onAddTask = { presenter.onAddTaskClicked() },
                onToggle = { id -> presenter.onToggleClicked(id) },
                onDeleteTask = { id -> presenter.onDeleteTaskClicked(id) },
                modifier = Modifier,
            )
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.start()
    }
}


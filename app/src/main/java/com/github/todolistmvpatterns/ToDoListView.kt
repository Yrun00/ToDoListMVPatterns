package com.github.todolistmvpatterns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.todolistmvpatterns.data.Task
import com.github.todolistmvpatterns.mvi.TodoListUiAction

@Composable
fun ToDoListView(
    inputText: String,
    tasks: List<Task>,
    createButtonEnabled: Boolean,
    onAction: (TodoListUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = inputText,
                onValueChange = { onAction(TodoListUiAction.InputChanged(it)) },
                singleLine = true,
                placeholder = { Text("Новая таска") },
            )
            Spacer(Modifier.width(12.dp))
            Button(
                onClick = { onAction(TodoListUiAction.AddClicked) },
                enabled = createButtonEnabled,
            ) {
                Text("Создать")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = tasks,
                key = { it.id },
            ) { task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { checked ->
                            onAction(TodoListUiAction.ToggleClicked(task.id))
                        },
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = task.title,
                        modifier = Modifier.weight(1f),
                        textDecoration = if (task.done) TextDecoration.LineThrough else null,
                    )
                    Button(onClick = { onAction(TodoListUiAction.DeleteClicked(task.id)) }) {
                        Text(
                            "Удалить",
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewToDoList() {
    ToDoListView(
        inputText = "",
        tasks = listOf(
            Task(
                1,
                "Купить молоко,сыр,хлеб, яблоки, помидоры и вообще тут очень длинный текст как это выглядит",
                false,
            ),
            Task(2, "Пробежка", true),
            Task(3, "Пробежка", true),
            Task(4, "Пробежка", true),
        ),
        onAction = {},
        createButtonEnabled = false,
    )
}

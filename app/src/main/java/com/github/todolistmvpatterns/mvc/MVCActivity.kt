package com.github.todolistmvpatterns.mvc

//@AndroidEntryPoint
//class MVCMainActivity : ComponentActivity(), MVCView {
//
////    @Inject
////    lateinit var controller: Controller
//    private var uiState by mutableStateOf(
//        MVCUiState(
//            inputedText = "",
//            tasks = emptyList(),
//            createButtonEnabled = false,
//        ),
//    )
//
//    override fun render(state: MVCUiState) {
//        uiState = state
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val restored = savedInstanceState?.getString(KEY_INPUT).orEmpty()
//        if (restored.isNotEmpty()) {
////            controller.onInputChanged(restored)
////        } else {
////            controller.start()
////        }
//        enableEdgeToEdge()
//        setContent {
//            ToDoListView(
//                inputText = uiState.inputedText,
//                tasks = uiState.tasks,
//                createButtonEnabled = uiState.createButtonEnabled,
////                onInputChange = { text -> controller.onInputChanged(text) },
////                onAddTask = { controller.onAddTaskClicked() },
////                onToggle = { id -> controller.onToggleClicked(id) },
////                onDeleteTask = { id -> controller.onDeleteTaskClicked(id) },
////                modifier = Modifier,
//            )
//        }
//    }
//
////    companion object {
////        private const val KEY_INPUT = "key_input"
////    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString(KEY_INPUT, uiState.inputedText)
//    }
//}


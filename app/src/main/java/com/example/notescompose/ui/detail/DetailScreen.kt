package com.example.notescompose.ui.detail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    upPressed: () -> Unit,
) {

    Scaffold(
        topBar = {
            DetailTopBar(
                viewModel = viewModel,
                upPressed = upPressed,
                onSave = {
                    when (viewModel.noteId.value) {
                        -1 -> {
                            viewModel.saveNote()
                        }
                        else -> {
                            viewModel.updateNote(viewModel.noteId.value)
                        }
                    }
                },
            )
        },
        content = {
            DetailContentScreen(
                viewModel = viewModel,
            )
        }
    )


}
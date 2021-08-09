package com.example.notescompose.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.notescompose.R
import com.example.notescompose.data.NoteEntity


@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onCreateNoteClick: () -> Unit,
    onNoteClick: (noteId: Int) -> Unit,
) {


    val selectedItems = remember { mutableStateListOf<NoteEntity>() }
    val isSearchMode by viewModel.isSearchMode.collectAsState()
    val isDeleteMode by viewModel.isDeleteMode.collectAsState()
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            if (isDeleteMode) {
                HomeTopBar(
                    viewModel = viewModel,
                    onDelete = {
                        viewModel.deleteNote(selectedItems)
                        viewModel.updateDeleteMode(false)
                        selectedItems.clear()
                    },
                    deleteItemCount = selectedItems.size,
                    onCloseDeleteMode = {
                        selectedItems.clear()
                        viewModel.updateDeleteMode(false)

                    },
                )
            } else {
                TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
                    Image(
                        painter = painterResource(id = R.drawable.notelogo),
                        contentDescription = stringResource(R.string.app_logo),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },

        content = {
            HomeContentScreen(
                selectedItems = selectedItems,
                viewModel = viewModel,
                onNoteClick = { noteId ->
                    onNoteClick(noteId)
                },
                onCloseSearchMode = {
                    viewModel.updateSearch("")
                    viewModel.updateSearchMode(false)
                    viewModel.getFilteredList("")
                    focusManager.clearFocus()
                },
            )
        },

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (!isSearchMode && !isDeleteMode) {
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    contentColor = Color.LightGray,
                    onClick = onCreateNoteClick,
                    shape = CircleShape,
                ) {
                    Icon(Icons.Default.Add, stringResource(R.string.add_new_note))
                }
            }
        }
    )
}

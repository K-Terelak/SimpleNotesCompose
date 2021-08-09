package com.example.notescompose.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notescompose.R
import com.example.notescompose.data.NoteEntity

@ExperimentalFoundationApi
@Composable
fun HomeContentScreen(
    viewModel: HomeViewModel,
    onNoteClick: (noteId: Int) -> Unit,
    selectedItems: SnapshotStateList<NoteEntity>,
    onCloseSearchMode: () -> Unit,
) {
    val notes = viewModel.notesFiltered.orEmpty()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        SearchBar(
            viewModel = viewModel,
            onCloseSearchMode = onCloseSearchMode,
        )
        StaggeredVerticalGrid(
            numColumns = 2,
            modifier = Modifier.padding(horizontal = 8.dp),
        ) {

            notes.forEach { note ->
                NoteItem(
                    selectedItems = selectedItems,
                    viewModel = viewModel,
                    note = note,
                    onNoteClick = { onNoteClick(it) },
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    viewModel: HomeViewModel,
    onCloseSearchMode: () -> Unit,
) {

    val isSearchMode by viewModel.isSearchMode.collectAsState()
    val search by viewModel.search.collectAsState()

    TextField(
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search)
            )
        },
        trailingIcon = {
            if (isSearchMode) {
                IconButton(
                    onClick = {
                        if (isSearchMode) {
                            onCloseSearchMode()
                        }
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close search mode."
                        )
                    }
                )
            }

        },
        textStyle = TextStyle(fontSize = 12.sp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.onPrimary,
            backgroundColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        shape = CircleShape,
        placeholder = { Text(text = stringResource(R.string.search), fontSize = 12.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp, top = 4.dp, start = 32.dp, end = 32.dp),
        singleLine = true,
        maxLines = 1,
        value = search,
        onValueChange = {
            viewModel.updateSearch(it)
            viewModel.getFilteredList(it)
            when (it) {
                "" -> {
                    viewModel.updateSearchMode(false)
                }
                else -> {
                    viewModel.updateSearchMode(true)
                }
            }
        }
    )


}





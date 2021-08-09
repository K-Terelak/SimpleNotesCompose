package com.example.notescompose.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.notescompose.R

@Composable
fun HomeTopBar(
    viewModel: HomeViewModel,
    onDelete: () -> Unit,
    deleteItemCount: Int,
    onCloseDeleteMode: () -> Unit,
) {

    val isDeleteMode by viewModel.isDeleteMode.collectAsState()

    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        title = {
            if (isDeleteMode) Text(text = "Selected: $deleteItemCount")
        },
        navigationIcon = {
            if (isDeleteMode) {
                IconButton(
                    onClick = {
                        onCloseDeleteMode()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_delete_mode)
                    )
                }
            }
        },
        actions = {
            if (isDeleteMode) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_selected_notes)
                    )
                }
            }
        }
    )
}
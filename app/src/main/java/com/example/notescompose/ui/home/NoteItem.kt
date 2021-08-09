package com.example.notescompose.ui.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notescompose.data.NoteEntity


@ExperimentalFoundationApi
@Composable
fun NoteItem(
    selectedItems: SnapshotStateList<NoteEntity>,
    viewModel: HomeViewModel,
    note: NoteEntity,
    onNoteClick: (noteId: Int) -> Unit,
) {

    val isDeleteMode by viewModel.isDeleteMode.collectAsState()

    Card(
        border = BorderStroke(
            1.dp,
            if (selectedItems.contains(note)) MaterialTheme.colors.error else MaterialTheme.colors.secondary
        ),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .combinedClickable(
                onClick = {
                    if (isDeleteMode) {
                        if (selectedItems.contains(note)) {
                            selectedItems.remove(note)
                            Log.e("NoteItem", "remove noteId=${note.id}")
                        } else {
                            selectedItems.add(note)
                            Log.e("NoteItem", "add noteId=${selectedItems.size}")

                        }

                    } else if (!isDeleteMode) {
                        onNoteClick(note.id)
                    }

                },
                onLongClick = {
                    if (!isDeleteMode) {
                        if (selectedItems.contains(note)) {
                            selectedItems.remove(note)
                            Log.e("NoteItem", "remove noteId=${note.id}")
                        } else {
                            selectedItems.add(note)
                            Log.e("NoteItem", "add noteId=${selectedItems.size}")
                        }
                    }
                    when (selectedItems.size) {
                        0 -> {
                            viewModel.updateDeleteMode(false)
                        }
                        else -> {
                            viewModel.updateDeleteMode(true)
                        }
                    }
                }
            )
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp,
                maxLines = 1,
                text = note.title ?: "",
                overflow = TextOverflow.Ellipsis,

            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                color = MaterialTheme.colors.onSecondary,
                fontSize = 16.sp,
                maxLines = 4,
                text = note.content ?: "",
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
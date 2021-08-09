package com.example.notescompose.ui.detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import com.example.notescompose.R

@Composable
fun DetailTopBar(
    viewModel: DetailViewModel,
    upPressed: () -> Unit,
    onSave: () -> Unit,
) {

    val needSave by viewModel.needSave.collectAsState()


    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        actions = {
            IconButton(onClick = onSave) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.save_note),
                    modifier = Modifier.alpha(if (needSave) 1f else 0.3f)
                )

            }
        },
        navigationIcon = {
            IconButton(onClick = upPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.go_back)
                )
            }

        },
        title = {}
    )
}

package com.example.notescompose.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notescompose.R

@Composable
fun DetailContentScreen(
    viewModel: DetailViewModel,
) {
    val title by viewModel.title.collectAsState()
    val content by viewModel.content.collectAsState()

    Column(modifier = Modifier.padding(horizontal = 8.dp)) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.title),
                    color = MaterialTheme.colors.onPrimary
                )
            },
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.onPrimary,
                textColor = MaterialTheme.colors.onPrimary,
            ),
            onValueChange = { viewModel.updateTitle(it) },
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = content,
            placeholder = {
                Text(
                    text = stringResource(R.string.content),
                    color = MaterialTheme.colors.onSecondary
                )
            },
            textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.onSecondary,
                textColor = MaterialTheme.colors.onSecondary,
            ),
            onValueChange = { viewModel.updateContent(it) },
        )

    }
}

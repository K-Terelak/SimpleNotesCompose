package com.example.notescompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NotesNavGraph(navController)

}
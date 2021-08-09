package com.example.notescompose.ui.navigation

import com.example.notescompose.util.Constants.Companion.ARGUMENT_NOTE_ID
import com.example.notescompose.util.Constants.Companion.SCREEN_DETAIL
import com.example.notescompose.util.Constants.Companion.SCREEN_HOME

sealed class Screens(
    val route: String
) {
    object Home : Screens(route = SCREEN_HOME)
    object Detail : Screens(route = "$SCREEN_DETAIL?$ARGUMENT_NOTE_ID={$ARGUMENT_NOTE_ID}") {
        fun createRoute(noteId: Int): String = "$SCREEN_DETAIL?$ARGUMENT_NOTE_ID=$noteId"
    }
}
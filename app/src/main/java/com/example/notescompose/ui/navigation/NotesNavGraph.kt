package com.example.notescompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.notescompose.ui.detail.DetailScreen
import com.example.notescompose.ui.detail.DetailViewModel
import com.example.notescompose.ui.home.HomeScreen
import com.example.notescompose.ui.home.HomeViewModel
import com.example.notescompose.util.Constants.Companion.ARGUMENT_NOTE_ID
import kotlinx.coroutines.FlowPreview

@ExperimentalFoundationApi
@FlowPreview
@Composable
fun NotesNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        addHome(navController)
        addDetails(navController)
    }
}


@ExperimentalFoundationApi
@FlowPreview
private fun NavGraphBuilder.addHome(navController: NavController) {
    composable(route = Screens.Home.route) {
        val viewModel: HomeViewModel = hiltViewModel()
        HomeScreen(
            viewModel = viewModel,
            onCreateNoteClick = { navController.navigate(Screens.Detail.route) },
            onNoteClick = { noteId -> navController.navigate(Screens.Detail.createRoute(noteId)) },
        )
    }
}

@FlowPreview
private fun NavGraphBuilder.addDetails(navController: NavController) {
    composable(
        route = Screens.Detail.route,
        arguments = listOf(
            navArgument(ARGUMENT_NOTE_ID) {
                defaultValue = -1
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val viewModel: DetailViewModel =
            hiltViewModel()

        val noteId =
            backStackEntry.arguments?.getInt(ARGUMENT_NOTE_ID)
                ?: throw IllegalStateException("'noteId' shouldn't be null")


        viewModel.updateNoteId(noteId)
        viewModel.getNote(noteId)
        DetailScreen(
            viewModel = viewModel,
            upPressed = { navController.popBackStack() },
        )
    }
}

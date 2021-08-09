package com.example.notescompose.data

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class NotesRepository @Inject constructor(
    localDataSource: LocalDataSource
)  {

    val local = localDataSource
}
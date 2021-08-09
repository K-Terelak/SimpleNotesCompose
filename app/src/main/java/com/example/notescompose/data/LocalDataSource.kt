package com.example.notescompose.data

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val notesDao: NotesDao,
) {

    suspend fun saveNote(noteEntity: NoteEntity):Long {
           return notesDao.insertNote(noteEntity)
    }

    fun getNoteById(noteId: Int): Flow<NoteEntity> {
        return notesDao.getNoteById(noteId)
    }

    fun getAllNotes(): Flow<List<NoteEntity>> {
        return notesDao.getAllNotes().flowOn(IO).conflate()
    }


    suspend fun deleteNote(noteEntity: NoteEntity) {
            notesDao.deleteNoteById(noteEntity.id)
    }

    suspend fun updateNote(noteEntity: NoteEntity) {
            notesDao.updateNote(noteEntity)
    }
}
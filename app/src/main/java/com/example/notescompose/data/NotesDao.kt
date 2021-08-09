package com.example.notescompose.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Query("SELECT * FROM notes_entity WHERE id=:noteId")
    fun getNoteById(noteId: Int): Flow<NoteEntity>

    @Query("SELECT * FROM notes_entity ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("DELETE FROM notes_entity WHERE id=:noteId")
    suspend fun deleteNoteById(noteId: Int)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)
}
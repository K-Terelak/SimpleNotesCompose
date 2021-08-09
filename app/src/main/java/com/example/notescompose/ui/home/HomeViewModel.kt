package com.example.notescompose.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notescompose.data.NoteEntity
import com.example.notescompose.data.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
) : ViewModel() {


    private val _isDeleteMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDeleteMode: StateFlow<Boolean> = _isDeleteMode

    private val _isSearchMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearchMode: StateFlow<Boolean> = _isSearchMode

    private val _search: MutableStateFlow<String> = MutableStateFlow("")
    val search: StateFlow<String> = _search

    private var notes: List<NoteEntity>? by mutableStateOf(null)
    var notesFiltered: List<NoteEntity>? by mutableStateOf(null)

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            notesRepository.local.getAllNotes().collect {
                notes = it
                getFilteredList(search.value)
            }
        }
    }

    fun getFilteredList(s: String) {
        viewModelScope.launch(IO) {
            notesFiltered = notes
                ?.filter {
                    it.title!!.contains(s.trim(), ignoreCase = true)
                            || it.content!!.contains(s.trim(), ignoreCase = true)
                }
        }
    }

    fun deleteNote(noteEntity: SnapshotStateList<NoteEntity>) {
        noteEntity.forEach {
            viewModelScope.launch(IO) {
                notesRepository.local.deleteNote(it)
            }
        }
    }

    fun updateDeleteMode(deleteMode: Boolean) {
        _isDeleteMode.value = deleteMode
    }


    fun updateSearchMode(searchMode: Boolean) {
        _isSearchMode.value = searchMode
    }

    fun updateSearch(search: String) {
        _search.value = search
    }




}
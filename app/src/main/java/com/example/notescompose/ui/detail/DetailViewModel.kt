package com.example.notescompose.ui.detail

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
class DetailViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {


    private var baseTitle:String = ""
    private var baseContent: String = ""

    private val _noteId: MutableStateFlow<Int> = MutableStateFlow(-1)
    val noteId: StateFlow<Int> = _noteId

    private val _title: MutableStateFlow<String> = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _content: MutableStateFlow<String> = MutableStateFlow("")
    val content: StateFlow<String> = _content

    private val _needSave: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val needSave: StateFlow<Boolean> = _needSave


    fun updateNote(noteId: Int) {
        viewModelScope.launch(IO) {
            notesRepository.local.updateNote(
                NoteEntity(
                    id = noteId,
                    title = _title.value,
                    content = _content.value
                )
            )
            baseTitle = _title.value
            baseContent = _content.value
            _needSave.value = false
        }
    }


    fun getNote(noteId: Int) {
        if (noteId != -1) {
            viewModelScope.launch(IO) {
                notesRepository.local.getNoteById(noteId).collect { currentArticle ->
                    _title.value = currentArticle.title ?: ""
                    _content.value = currentArticle.content ?: ""
                    baseTitle = currentArticle.title ?: ""
                    baseContent = currentArticle.content ?: ""
                }
            }
        }
    }

    fun saveNote() {
        viewModelScope.launch(IO) {
            _noteId.value = notesRepository.local.saveNote(
                NoteEntity(
                    title = _title.value,
                    content = _content.value
                )
            ).toInt()
        }
    }


    fun updateNoteId(noteId: Int) {
        _noteId.value = noteId
    }

    fun updateTitle(title: String) {
        _title.value = title
        updateSave()
    }

    fun updateContent(content: String) {
        _content.value = content
        updateSave()
    }

    fun updateSave() {
        _needSave.value =
            !(baseTitle == _title.value && baseContent == _content.value)
    }

}
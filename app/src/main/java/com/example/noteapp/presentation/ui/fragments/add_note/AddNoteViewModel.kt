package com.example.noteapp.presentation.ui.fragments.add_note

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecases.CreateNoteUseCase
import com.example.noteapp.domain.usecases.EditNoteUseCase
import com.example.noteapp.presentation.ui.base.BaseViewModel
import com.example.noteapp.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
) : BaseViewModel() {

    private val _addNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val addNoteState = _addNoteState.asStateFlow()

    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNote(note: Note) {
        createNoteUseCase(note).collectData(_addNoteState)
    }

    fun editNote(note: Note) {
        editNoteUseCase(note).collectData(_editNoteState)
    }
}
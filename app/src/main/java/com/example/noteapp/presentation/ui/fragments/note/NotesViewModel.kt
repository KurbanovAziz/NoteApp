package com.example.noteapp.presentation.ui.fragments.note


import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecases.DeleteUseCase
import com.example.noteapp.domain.usecases.GetAllNotesUseCase
import com.example.architecnotes.presentation.ui.base.BaseViewModel
import com.example.noteapp.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteUseCase: DeleteUseCase,
) : BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNotesState = _deleteNoteState.asStateFlow()

    fun deleteNote(note: Note) {
        deleteUseCase(note).collectData(_deleteNoteState)
    }

    fun getAllNotes() {
        getAllNotesUseCase().collectData(_getAllNotesState)
    }
}
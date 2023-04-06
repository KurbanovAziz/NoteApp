package com.example.noteapp.domain.usecases


import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val a: String,
) {

    fun editNote(note: Note) = noteRepository.editNote(note)
}
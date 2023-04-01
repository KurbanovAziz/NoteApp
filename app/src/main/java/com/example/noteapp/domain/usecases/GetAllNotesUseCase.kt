package com.example.noteapp.domain.usecases

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repository.NoteRepository

class GetAllNotesUseCase constructor(private val noteRepository: NoteRepository) {

    fun getAllNotes(note: Note) = noteRepository.getAllNotes()
}
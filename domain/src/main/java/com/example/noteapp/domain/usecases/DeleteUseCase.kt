package com.example.noteapp.domain.usecases

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor
    (private val noteRepository: NoteRepository
) {
    operator fun invoke (note: Note) = noteRepository.delete(note)
}
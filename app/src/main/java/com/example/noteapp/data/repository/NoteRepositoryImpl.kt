package com.example.noteapp.data.repository

import com.example.noteapp.data.base.BaseRepository
import com.example.noteapp.data.local.NoteDao
import com.example.noteapp.data.mapper.toEntity
import com.example.noteapp.data.mapper.toNote
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDAo: NoteDao
) : NoteRepository,
    BaseRepository() {

    override fun createNote(note: Note) = doRequest {
        noteDAo.createNote(note.toEntity())
    }

    override fun getAllNotes() = doRequest {
        noteDAo.getAllNotes().map {
            it.toNote()
        }
    }


    override fun editNote(note: Note) = doRequest {
        noteDAo.editNote(note.toEntity())
    }

    override fun deleteNote(note: Note) = doRequest {
        noteDAo.deleteNote(note.toEntity())
    }
}
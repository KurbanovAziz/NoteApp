package com.example.noteapp.data.repository

import com.example.noteapp.data.local.NoteDao
import com.example.noteapp.data.mapper.toEntity
import com.example.noteapp.data.mapper.toNote
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun createNote(note: Note) {
        noteDao.createNote(note.toEntity())
    }

    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes().map {
            it.toNote()
        }
    }

    override fun editNote(note: Note) {
        noteDao.editNote(note.toEntity())
    }

    override fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toEntity())
    }
}
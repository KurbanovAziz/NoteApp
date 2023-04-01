package com.example.noteapp.data.mapper

import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.domain.model.Note

fun Note.toEntity() = NoteEntity(
    id, tittle, description
)

fun NoteEntity.toNote() = Note(
    id, tittle, description
)
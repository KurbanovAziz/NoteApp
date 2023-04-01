package com.example.noteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notes")
class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tittle : String,
    val description : String
        )
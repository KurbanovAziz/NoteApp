package com.example.noteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

 @Entity(tableName = "notes")
class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val description:String
)
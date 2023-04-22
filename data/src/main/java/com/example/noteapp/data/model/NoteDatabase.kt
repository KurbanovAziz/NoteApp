package com.example.noteapp.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.local.NoteDao

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase(){

    abstract fun noteDao(): NoteDao
}
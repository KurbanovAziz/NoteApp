package com.example.noteapp.domain.model

class Note(
    val id: Int = DEFAULT_ID,
    val tittle: String,
    val description: String
) {
    companion object {
        const val  DEFAULT_ID = 0
    }
}
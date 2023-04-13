package com.example.noteapp.domain.model

class Note(
    val id: Int = DEFAULT_ID,
    var title: String = "",
    var descriptions: String = "",
):java.io.Serializable
{
    companion object{
        const val DEFAULT_ID = 0
    }
}
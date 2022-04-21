package com.herdal.notepad.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.herdal.notepad.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)
    @Query("SELECT * FROM notes ORDER BY creation_date ASC")
    fun listAllNotes() : LiveData<List<Note>>
    @Delete
    suspend fun deleteNote(note: Note)
    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()
}
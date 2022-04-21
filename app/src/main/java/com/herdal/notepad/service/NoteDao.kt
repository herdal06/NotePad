package com.herdal.notepad.service

import androidx.lifecycle.LiveData
import androidx.room.*
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
    @Update
    suspend fun updateNote(note: Note)
}
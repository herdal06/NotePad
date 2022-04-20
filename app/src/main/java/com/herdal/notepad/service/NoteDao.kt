package com.herdal.notepad.service

import androidx.room.Dao
import androidx.room.Insert
import com.herdal.notepad.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)
}
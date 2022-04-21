package com.herdal.notepad.repository

import androidx.lifecycle.LiveData
import com.herdal.notepad.model.Note
import com.herdal.notepad.service.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val listAllNotes: LiveData<List<Note>> = noteDao.listAllNotes()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}
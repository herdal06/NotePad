package com.herdal.notepad.repository

import com.herdal.notepad.model.Note
import com.herdal.notepad.service.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

}
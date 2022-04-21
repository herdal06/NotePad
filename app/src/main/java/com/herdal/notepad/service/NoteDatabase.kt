package com.herdal.notepad.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.herdal.notepad.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile // visible to other threads
        private var INSTANCE: NoteDatabase? = null

        fun createDatabase(context: Context) : NoteDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) // already exists
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
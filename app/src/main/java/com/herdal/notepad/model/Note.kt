package com.herdal.notepad.model

import android.icu.text.SimpleDateFormat
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "creation_date")
    val creationDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
) : Parcelable {
}
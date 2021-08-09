package com.example.notescompose.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notescompose.util.Constants.Companion.ENTITY_NOTES
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ENTITY_NOTES)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    @ColumnInfo(name = "title")
    val title: String? ="",
    @ColumnInfo(name = "content")
    val content: String? ="",
):Parcelable

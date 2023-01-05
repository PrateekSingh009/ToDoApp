package com.example.todoapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tableTask")
class Task(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}
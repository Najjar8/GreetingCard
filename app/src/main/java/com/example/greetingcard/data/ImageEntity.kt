package com.example.greetingcard.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val link: String = ""
)
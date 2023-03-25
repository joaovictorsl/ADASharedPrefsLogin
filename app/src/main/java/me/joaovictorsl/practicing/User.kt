package me.joaovictorsl.practicing

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User (
    @PrimaryKey
    val id: String,
    val filePath: String
)
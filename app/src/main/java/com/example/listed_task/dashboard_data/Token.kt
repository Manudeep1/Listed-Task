package com.example.listed_task.dashboard_data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Token (
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "Token")
    val token : String
        )
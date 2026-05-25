package com.teamstandup.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "standups")
data class StandupEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val teamId: String,
    val text: String,
    val mood: String,
    val blockers: String,
    val voiceUrl: String?,
    val timestamp: Long,
    val syncStatus: String
)

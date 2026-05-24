package com.teamstandup.model

enum class Mood { HAPPY, NEUTRAL, SAD }
enum class SyncStatus { LOCAL, SYNCED, FAILED }

data class Standup(
    val id: String = "",
    val userId: String = "",
    val text: String = "",
    val mood: Mood = Mood.NEUTRAL,
    val blockers: List<String> = emptyList(),
    val voiceUrl: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val syncStatus: SyncStatus = SyncStatus.LOCAL
)

package com.teamstandup.model

data class Team(
    val id: String = "",
    val name: String = "",
    val memberIds: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)

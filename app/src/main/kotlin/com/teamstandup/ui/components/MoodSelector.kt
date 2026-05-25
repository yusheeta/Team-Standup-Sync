package com.teamstandup.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamstandup.model.Mood

@Composable
fun MoodSelector(selected: Mood, onSelect: (Mood) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Mood.values().forEach { mood ->
            val emoji = when (mood) {
                Mood.HAPPY -> "😊 Happy"
                Mood.NEUTRAL -> "😐 Okay"
                Mood.SAD -> "😞 Blocked"
            }
            FilterChip(
                selected = selected == mood,
                onClick = { onSelect(mood) },
                label = { Text(emoji) }
            )
        }
    }
}

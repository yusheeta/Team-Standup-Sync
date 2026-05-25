package com.teamstandup.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamstandup.model.Mood
import com.teamstandup.model.Standup
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun StandupCard(standup: Standup) {
    val moodEmoji = when (standup.mood) {
        Mood.HAPPY -> "😊"
        Mood.NEUTRAL -> "😐"
        Mood.SAD -> "😞"
    }
    val dateStr = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())
        .format(Date(standup.timestamp))

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(moodEmoji, style = MaterialTheme.typography.titleLarge)
                Text(dateStr, style = MaterialTheme.typography.bodySmall)
            }
            Text(standup.text, style = MaterialTheme.typography.bodyMedium)
            if (standup.blockers.isNotEmpty()) {
                Text("Blockers:", style = MaterialTheme.typography.labelMedium)
                standup.blockers.forEach { blocker ->
                    Text("• $blocker", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

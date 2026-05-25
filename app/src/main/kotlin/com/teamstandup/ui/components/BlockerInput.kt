package com.teamstandup.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BlockerInput(blockers: List<String>, onBlockersChange: (List<String>) -> Unit) {
    var newBlocker by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        blockers.forEach { blocker ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("• $blocker", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                IconButton(onClick = { onBlockersChange(blockers - blocker) }) {
                    Icon(Icons.Default.Close, contentDescription = "Remove blocker")
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = newBlocker,
                onValueChange = { newBlocker = it },
                placeholder = { Text("Add a blocker...") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            IconButton(
                onClick = {
                    if (newBlocker.isNotBlank()) {
                        onBlockersChange(blockers + newBlocker.trim())
                        newBlocker = ""
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add blocker")
            }
        }
    }
}

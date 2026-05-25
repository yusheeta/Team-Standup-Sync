package com.teamstandup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.teamstandup.model.Mood
import com.teamstandup.ui.components.BlockerInput
import com.teamstandup.ui.components.MoodSelector
import com.teamstandup.ui.viewmodels.StandupViewModel
import com.teamstandup.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandupInputScreen(
    onSubmit: () -> Unit,
    standupViewModel: StandupViewModel = viewModel()
) {
    var standupText by remember { mutableStateOf("") }
    var selectedMood by remember { mutableStateOf(Mood.NEUTRAL) }
    var blockers by remember { mutableStateOf(listOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Today's Standup") },
                navigationIcon = {
                    IconButton(onClick = onSubmit) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("What did you work on?", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = standupText,
                onValueChange = { standupText = it },
                placeholder = { Text("Describe your progress...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )

            Text("How are you feeling?", style = MaterialTheme.typography.titleMedium)
            MoodSelector(selected = selectedMood, onSelect = { selectedMood = it })

            Text("Any blockers?", style = MaterialTheme.typography.titleMedium)
            BlockerInput(blockers = blockers, onBlockersChange = { blockers = it })

            Button(
                onClick = {
                    standupViewModel.createStandup(
                        teamId = Constants.DEFAULT_TEAM_ID,
                        text = standupText,
                        mood = selectedMood,
                        blockers = blockers
                    )
                    onSubmit()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = standupText.isNotBlank()
            ) {
                Text("Submit Standup")
            }
        }
    }
}

package com.teamstandup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.teamstandup.ui.components.StandupCard
import com.teamstandup.ui.viewmodels.StandupViewModel
import com.teamstandup.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamStandupListScreen(
    onAddStandup: () -> Unit,
    onProfile: () -> Unit,
    standupViewModel: StandupViewModel = viewModel()
) {
    val uiState by standupViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        standupViewModel.loadStandups(Constants.DEFAULT_TEAM_ID)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Team Standups") },
                actions = {
                    IconButton(onClick = onProfile) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddStandup) {
                Icon(Icons.Default.Add, contentDescription = "Add Standup")
            }
        }
    ) { padding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }
            uiState.standups.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) { Text("No standups yet. Be the first!") }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.standups) { standup ->
                        StandupCard(standup = standup)
                    }
                }
            }
        }
    }
}

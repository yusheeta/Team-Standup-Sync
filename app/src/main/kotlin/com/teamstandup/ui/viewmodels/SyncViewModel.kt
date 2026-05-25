package com.teamstandup.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.teamstandup.model.SyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SyncViewModel : ViewModel() {
	private val _syncState = MutableStateFlow(SyncState.IDLE)
	val syncState: StateFlow<SyncState> = _syncState

	fun setSyncing() { _syncState.value = SyncState.SYNCING }
	fun setSuccess() { _syncState.value = SyncState.SUCCESS }
	fun setFailed() { _syncState.value = SyncState.FAILED }
	fun setIdle() { _syncState.value = SyncState.IDLE }
}

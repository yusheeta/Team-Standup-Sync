package com.teamstandup.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.teamstandup.model.Mood
import com.teamstandup.model.Standup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class StandupUiState(
	val standups: List<Standup> = emptyList(),
	val isLoading: Boolean = false,
	val error: String? = null
)

class StandupViewModel : ViewModel() {
	private val db = FirebaseFirestore.getInstance()
	private val auth = FirebaseAuth.getInstance()

	private val _uiState = MutableStateFlow(StandupUiState(isLoading = true))
	val uiState: StateFlow<StandupUiState> = _uiState

	fun loadStandups(teamId: String) {
		db.collection("teams").document(teamId).collection("standups")
			.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
			.addSnapshotListener { snapshot, error ->
				if (error != null) {
					_uiState.value = _uiState.value.copy(isLoading = false, error = error.message)
					return@addSnapshotListener
				}
				val list = snapshot?.documents?.mapNotNull { doc ->
					doc.toObject(Standup::class.java)?.copy(id = doc.id)
				} ?: emptyList()
				_uiState.value = StandupUiState(standups = list)
			}
	}

	fun createStandup(teamId: String, text: String, mood: Mood, blockers: List<String>) {
		val userId = auth.currentUser?.uid ?: return
		val standup = hashMapOf(
			"userId" to userId,
			"text" to text,
			"mood" to mood.name,
			"blockers" to blockers,
			"voiceUrl" to null,
			"timestamp" to System.currentTimeMillis(),
			"syncStatus" to "SYNCED"
		)
		viewModelScope.launch {
			try {
				db.collection("teams").document(teamId)
					.collection("standups")
					.add(standup)
					.await()
			} catch (e: Exception) {
				_uiState.value = _uiState.value.copy(error = e.message)
			}
		}
	}
}

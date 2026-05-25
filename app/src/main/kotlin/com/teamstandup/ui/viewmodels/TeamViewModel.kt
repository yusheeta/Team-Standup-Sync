package com.teamstandup.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.teamstandup.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TeamViewModel : ViewModel() {
	private val db = FirebaseFirestore.getInstance()
	private val _team = MutableStateFlow<Team?>(null)
	val team: StateFlow<Team?> = _team

	fun loadTeam(teamId: String) {
		db.collection("teams").document(teamId)
			.addSnapshotListener { snapshot, _ ->
				snapshot?.let { doc ->
					_team.value = doc.toObject(Team::class.java)?.copy(id = doc.id)
				}
			}
	}
}

package com.teamstandup.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.teamstandup.model.Standup
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    val currentUserId: String? get() = auth.currentUser?.uid

    fun isReady(): Boolean = auth.currentUser != null

    suspend fun addStandup(teamId: String, standup: Standup): String {
        val ref = db.collection("teams").document(teamId)
            .collection("standups")
            .add(standup)
            .await()
        return ref.id
    }

    suspend fun getStandups(teamId: String): List<Standup> {
        val snapshot = db.collection("teams").document(teamId)
            .collection("standups")
            .get()
            .await()
        return snapshot.documents.mapNotNull {
            it.toObject(Standup::class.java)?.copy(id = it.id)
        }
    }
}

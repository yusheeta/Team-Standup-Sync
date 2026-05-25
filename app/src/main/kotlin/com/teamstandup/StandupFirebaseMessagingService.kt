package com.teamstandup

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class StandupFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // Handle standup reminder push notification
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Update FCM token in Firestore for this user
    }
}

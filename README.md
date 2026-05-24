# Team Standup Sync

Live async standup app for teams using Android + Firebase + Python Cloud Functions.

## Why this project
- Full-stack ownership: Android client + backend + integrations
- Real-time collaboration with offline-first behavior
- Practical integrations: Slack + Claude
- Product metrics: DAU, completion rate, team sentiment

## Architecture
- Android: Kotlin + Jetpack Compose + MVVM + Repository
- Local cache: Room
- Backend: Firebase (Auth, Firestore, Cloud Messaging)
- Serverless: Python 3.11 Cloud Functions

## Monorepo Structure
```
app/
  src/main/kotlin/com/teamstandup/
    ui/screens/
    ui/components/
    ui/viewmodels/
    data/repository/
    data/local/
    data/remote/
    model/
    util/
functions/
  main.py
  slack_integration.py
  claude_summary.py
  sentiment_analysis.py
  auth.py
firebase.json
firestore.rules
```

## MVP Features (Phase 1)
- Firebase Auth (email + Google)
- Create/join team
- Submit daily standup: text + mood + blockers
- Team standup list with real-time updates
- Offline queue + background sync
- Slack webhook notification and reminders
- Daily summary + mood aggregation

## Local Setup
1. Install Android Studio (latest stable), JDK 17.
2. Install Firebase CLI:
   ```bash
   npm install -g firebase-tools
   firebase login
   ```
3. Android app setup:
   - Create/open Android module in this repo.
   - Add `google-services.json` under `app/`.
4. Functions setup:
   ```bash
   cd functions
   python3 -m venv .venv
   source .venv/bin/activate
   pip install -r requirements.txt
   ```
5. Configure environment:
   - Copy `.env.example` to `.env` and fill values.

## Firestore Model (MVP)
- `teams/{teamId}`
  - metadata: `name`, `createdAt`, `memberIds`
- `teams/{teamId}/standups/{standupId}`
  - `userId`, `text`, `mood`, `blockers[]`, `voiceUrl`, `timestamp`, `syncStatus`
- `users/{userId}`
  - `name`, `email`, `teamIds[]`, `slackHandle`, `timezone`

## Week 1 Deliverables
- Android app bootstrapped with Compose entry screen
- Firebase project connected and Auth enabled
- Firestore rules in place
- Cloud Functions deployed with placeholder handlers

## Publish
After creating a GitHub repo:
```bash
git init
git add .
git commit -m "Initial project scaffold"
git remote add origin <your-github-repo-url>
git branch -M main
git push -u origin main
```

## Resume Bullet
Built and shipped Team Standup Sync (Android + Python backend). Implemented offline-first standup sync with Room + Firestore, integrated Slack notifications and Claude-powered summaries, and tracked submission/completion metrics across beta teams.

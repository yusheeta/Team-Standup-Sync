"""Cloud Functions entrypoint for Team Standup Sync."""

from slack_integration import on_standup_created, scheduled_reminder
from claude_summary import summarize_team_standups, detect_blockers
from sentiment_analysis import compute_team_sentiment, anomaly_detection

# Export placeholders for function discovery in deployment tooling.
__all__ = [
    "on_standup_created",
    "scheduled_reminder",
    "summarize_team_standups",
    "detect_blockers",
    "compute_team_sentiment",
    "anomaly_detection",
]

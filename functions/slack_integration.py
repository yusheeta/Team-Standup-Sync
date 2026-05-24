"""Slack integration handlers."""


def on_standup_created(event=None):
    """Post standup updates to a Slack channel."""
    return {"status": "ok", "handler": "on_standup_created"}


def scheduled_reminder(event=None):
    """Send reminder for missing standups."""
    return {"status": "ok", "handler": "scheduled_reminder"}

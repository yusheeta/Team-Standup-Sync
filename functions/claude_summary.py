"""Claude summary integration handlers."""


def summarize_team_standups(event=None):
    """Aggregate and summarize daily standups."""
    return {"status": "ok", "handler": "summarize_team_standups"}


def detect_blockers(event=None):
    """Extract blockers from standup text."""
    return {"status": "ok", "handler": "detect_blockers"}

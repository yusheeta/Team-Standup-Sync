"""Team sentiment and anomaly handlers."""


def compute_team_sentiment(event=None):
    """Compute daily team mood score from standups."""
    return {"status": "ok", "handler": "compute_team_sentiment"}


def anomaly_detection(event=None):
    """Flag unusual sentiment/blocker patterns."""
    return {"status": "ok", "handler": "anomaly_detection"}

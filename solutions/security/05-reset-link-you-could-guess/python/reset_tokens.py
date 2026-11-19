import secrets
from datetime import datetime, timedelta

LIFETIME = timedelta(minutes=15)


def issue(store: dict, user: str, now: datetime) -> str:
    """Create a reset token for `user`: 32 random bytes from the operating system's secure generator."""
    token = secrets.token_urlsafe(32)
    store[token] = (user, now + LIFETIME)
    return token


def redeem(store: dict, token: str, now: datetime) -> str | None:
    """The user this token resets, or None. A token is removed when it is used, so it works once."""
    entry = store.pop(token, None)
    if entry is None:
        return None
    user, expires = entry
    return user if now <= expires else None

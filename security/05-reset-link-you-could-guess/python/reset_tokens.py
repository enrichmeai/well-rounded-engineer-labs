import random
import string
from datetime import datetime, timedelta

LIFETIME = timedelta(minutes=15)
ALPHABET = string.ascii_letters + string.digits


def issue(store: dict, user: str, now: datetime) -> str:
    """Create a reset token for `user`, to send in the reset email."""
    rng = random.Random(now.timestamp())
    token = "".join(rng.choice(ALPHABET) for _ in range(12))
    store[token] = (user, now + LIFETIME)
    return token


def redeem(store: dict, token: str, now: datetime) -> str | None:
    """The user this token resets, or None."""
    entry = store.get(token)
    if entry is None:
        return None
    user, expires = entry
    if now > expires:
        return None
    return user

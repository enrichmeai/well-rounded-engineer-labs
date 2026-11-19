import base64
from datetime import datetime, timedelta

from reset_tokens import issue, redeem

NOW = datetime(2026, 11, 19, 9, 30, 0)


def randomness(token: str) -> int:
    """How many bytes a URL-safe base64 token carries."""
    return len(base64.urlsafe_b64decode(token + "=" * (-len(token) % 4)))


def test_two_tokens_at_the_same_moment_differ():
    store = {}
    assert issue(store, "ana", NOW) != issue(store, "ben", NOW)


def test_a_token_carries_at_least_32_bytes():
    assert randomness(issue({}, "ana", NOW)) >= 32


def test_a_token_works_once():
    store = {}
    token = issue(store, "ana", NOW)
    assert redeem(store, token, NOW + timedelta(minutes=1)) == "ana"
    assert redeem(store, token, NOW + timedelta(minutes=2)) is None


def test_a_token_expires_after_15_minutes():
    store = {}
    token = issue(store, "ana", NOW)
    assert redeem(store, token, NOW + timedelta(minutes=16)) is None

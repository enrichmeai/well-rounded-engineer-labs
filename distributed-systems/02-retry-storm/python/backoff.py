"""Drill 02: the retry storm. Make test_backoff.py pass."""

import random


def backoff_delays(attempts: int, rng: random.Random, base: float = 0.1, cap: float = 5.0) -> list[float]:
    """How long to wait, in seconds, before each retry."""
    return [base] * attempts

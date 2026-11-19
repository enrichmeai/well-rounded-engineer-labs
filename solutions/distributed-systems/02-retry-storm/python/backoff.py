"""Drill 02, solved: exponential backoff with full jitter."""

import random


def backoff_delays(attempts: int, rng: random.Random, base: float = 0.1, cap: float = 5.0) -> list[float]:
    """Attempt n waits a random time between 0 and min(cap, base * 2**n)."""
    return [rng.uniform(0, min(cap, base * 2 ** n)) for n in range(attempts)]

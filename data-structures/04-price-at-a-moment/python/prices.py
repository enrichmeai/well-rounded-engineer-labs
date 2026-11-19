"""Drill 04: the price at a moment. Make test_prices.py pass, fast.

times[i] is when prices[i] took effect; times is sorted, oldest first.
"""


def price_at(times: list[int], prices: list[int], at: int) -> int | None:
    """The price in effect at time `at`: the last one that took effect at or before it. None if none had yet."""
    found = None
    for t, p in zip(times, prices):
        if t <= at:
            found = p
        else:
            break
    return found

"""Drill 04, solved: the times are sorted, so binary search finds the moment in O(log n)."""

import bisect


def price_at(times: list[int], prices: list[int], at: int) -> int | None:
    i = bisect.bisect_right(times, at) - 1      # index of the last time <= at
    return prices[i] if i >= 0 else None

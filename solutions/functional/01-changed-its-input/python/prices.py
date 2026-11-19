"""Drill 01, solved: both functions build new lists and never write to the one they were given."""


def with_tax(prices: list[float], rate: float) -> list[float]:
    return [round(p * (1 + rate), 2) for p in prices]


def cheapest_three(prices: list[float]) -> list[float]:
    return sorted(prices)[:3]      # sorted() returns a new list; list.sort() changes the old one

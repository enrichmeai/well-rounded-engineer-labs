"""Drill 01: the function that changed its input. Make test_prices.py pass."""


def with_tax(prices: list[float], rate: float) -> list[float]:
    for i in range(len(prices)):
        prices[i] = round(prices[i] * (1 + rate), 2)
    return prices


def cheapest_three(prices: list[float]) -> list[float]:
    prices.sort()
    return prices[:3]

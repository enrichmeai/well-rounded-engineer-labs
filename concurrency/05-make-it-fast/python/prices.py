"""Drill 05: make it fast. Make test_prices.py pass. Do not change fetch_price()."""

import time

PRICES = {f"sku-{i}": 100 + i for i in range(20)}


def fetch_price(sku: str) -> int:
    """Stands in for a call to a price service: 0.1 seconds of waiting on the network."""
    time.sleep(0.1)
    return PRICES[sku]


def fetch_all(skus: list[str]) -> list[int]:
    return [fetch_price(sku) for sku in skus]

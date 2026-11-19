"""Drill 05, solved: the 20 waits overlap on a thread pool."""

import time
from concurrent.futures import ThreadPoolExecutor

PRICES = {f"sku-{i}": 100 + i for i in range(20)}


def fetch_price(sku: str) -> int:
    time.sleep(0.1)
    return PRICES[sku]


def fetch_all(skus: list[str]) -> list[int]:
    with ThreadPoolExecutor(max_workers=len(skus) or 1) as pool:
        return list(pool.map(fetch_price, skus))   # map keeps the input order

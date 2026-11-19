"""Drill 02: the key that cannot be found. Make test_stock.py pass. Keep SkuKey as the dict key."""


class SkuKey:
    def __init__(self, sku: str, size: int) -> None:
        self.sku = sku
        self.size = size

    def __eq__(self, other: object) -> bool:
        return isinstance(other, SkuKey) and (self.sku, self.size) == (other.sku, other.size)


class Stock:
    def __init__(self) -> None:
        self.counts: dict[SkuKey, int] = {}

    def add(self, sku: str, size: int, n: int) -> None:
        key = SkuKey(sku, size)
        self.counts[key] = self.counts.get(key, 0) + n

    def in_stock(self, sku: str, size: int) -> int:
        return self.counts.get(SkuKey(sku, size), 0)

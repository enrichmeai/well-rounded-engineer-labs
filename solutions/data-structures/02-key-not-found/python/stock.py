"""Drill 02, solved: SkuKey hashes the same fields it compares."""


class SkuKey:
    def __init__(self, sku: str, size: int) -> None:
        self.sku = sku
        self.size = size

    def __eq__(self, other: object) -> bool:
        return isinstance(other, SkuKey) and (self.sku, self.size) == (other.sku, other.size)

    def __hash__(self) -> int:
        return hash((self.sku, self.size))      # the same fields as __eq__


class Stock:
    def __init__(self) -> None:
        self.counts: dict[SkuKey, int] = {}

    def add(self, sku: str, size: int, n: int) -> None:
        key = SkuKey(sku, size)
        self.counts[key] = self.counts.get(key, 0) + n

    def in_stock(self, sku: str, size: int) -> int:
        return self.counts.get(SkuKey(sku, size), 0)

"""Drill 05: the cache that forgets the wrong thing. Make test_cache.py pass."""

from collections import OrderedDict


class Cache:
    def __init__(self, capacity: int) -> None:
        self.capacity = capacity
        self.data: OrderedDict = OrderedDict()   # oldest first
        self.evicted: list = []

    def get(self, key):
        return self.data.get(key)

    def put(self, key, value) -> None:
        self.data[key] = value
        if len(self.data) > self.capacity:
            oldest, _ = self.data.popitem(last=False)
            self.evicted.append(oldest)

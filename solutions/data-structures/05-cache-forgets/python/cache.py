"""Drill 05, solved: every read and update moves the entry to the most recent end."""

from collections import OrderedDict


class Cache:
    def __init__(self, capacity: int) -> None:
        self.capacity = capacity
        self.data: OrderedDict = OrderedDict()   # least recently used first
        self.evicted: list = []

    def get(self, key):
        if key not in self.data:
            return None
        self.data.move_to_end(key)               # a read is a use
        return self.data[key]

    def put(self, key, value) -> None:
        if key in self.data:
            self.data.move_to_end(key)           # so is an update
        self.data[key] = value
        if len(self.data) > self.capacity:
            oldest, _ = self.data.popitem(last=False)
            self.evicted.append(oldest)

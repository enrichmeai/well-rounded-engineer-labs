# Why the fix works

The cache kept entries in the order they were added and threw out the oldest: first in, first out. The home page
products were added first and read all day, so they were the first to go, while products nobody read sat safely at the
other end.

A cache is meant to keep what is used. Least recently used (LRU) eviction does that: every read or update moves the
entry to the "most recent" end, and eviction takes from the other end. In Python that is `OrderedDict.move_to_end`;
in Java, a `LinkedHashMap` built with `accessOrder = true` does it for you on every `get` and `put`.

**Other rules exist, by requirement:** least frequently used (LFU) keeps popular items through a burst of one-off reads;
a time to live (TTL) bounds how stale an entry can be. Real caches such as Caffeine combine recency, frequency and TTL.
Plain LRU is the usual starting point.

See an LRU cache built by hand in [`lru.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/lru.py) and the [Caching](https://enrichmeai.com/well-rounded-engineer/caching/) page of the guide.

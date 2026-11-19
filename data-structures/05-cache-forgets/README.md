# 05 · The cache that forgets the wrong thing

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Data Structures &amp; Algorithms](https://enrichmeai.com/well-rounded-engineer/dsa/) in the guide

## The story

A product cache holds the 1,000 most recently used products. The home page shows the same ten products all day, yet they keep missing the cache and hitting the database. Products nobody has looked at for hours stay cached.

## Before you start: predict

The cache evicts the entry that was **added** longest ago. The home page products were added first thing in the morning and read constantly since. Which entries does it throw out first?

## Your task

Make the tests pass: evict the **least recently used** entry, where reading or updating an entry counts as using it.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out CacheTest
```

Or, from the top of the repo: `python3 practice.py check 05 --track data-structures` (add `--java` for Java).

Stuck? `python3 practice.py hint 05 --track data-structures`. The worked answer and why it works are in
[`solutions/data-structures/05-cache-forgets/`](../../solutions/data-structures/05-cache-forgets/). Try first.

## Done when

- Reading an entry protects it from the next eviction.
- You can say why first-in-first-out is the wrong rule for a cache.

## Then

- An LRU cache built by hand from a dict and a doubly linked list: [`lru.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/lru.py) and [`java/Lru.java`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/java/Lru.java).

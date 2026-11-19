# 02 · The key that cannot be found

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Data Structures &amp; Algorithms](https://enrichmeai.com/well-rounded-engineer/dsa/) in the guide

## The story

The stock service counts items by SKU and size. Staff add stock all day. At closing time the report says the shop has none of anything. In Java nothing failed and nothing was logged; in Python the service would not even start.

## Before you start: predict

A hash map finds a key in two steps: the key's hash picks a bucket, then `equals` finds it in that bucket. If two keys are equal but have different hashes, which step goes wrong?

## Your task

Make the tests pass. Keep `SkuKey` as the key: it is used all over the real service.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out StockTest
```

Or, from the top of the repo: `python3 practice.py check 02 --track data-structures` (add `--java` for Java).

Stuck? `python3 practice.py hint 02 --track data-structures`. The worked answer and why it works are in
[`solutions/data-structures/02-key-not-found/`](../../solutions/data-structures/02-key-not-found/). Try first.

## Done when

- Adding 5 and then 3 of the same SKU and size reports 8.
- You can state the rule that links equality and hashing.

## Then

- A hash table written out by hand, with buckets and collisions: [`hash_table.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/hash_table.py), and what Java's `HashMap` does with a bad hash: [`java/HashMapTrees.java`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/java/HashMapTrees.java).

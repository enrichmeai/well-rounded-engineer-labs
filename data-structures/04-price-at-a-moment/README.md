# 04 · The price at a moment

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Data Structures &amp; Algorithms](https://enrichmeai.com/well-rounded-engineer/dsa/) in the guide

## The story

Every price change is recorded with the time it took effect, oldest first. Refunds must use the price that was in effect when the order was placed. The refund job for one busy day did not finish before the next one started.

## Before you start: predict

For each refund, the code walks the price history from the start until it passes the order time. With 100,000 price changes and 5,000 refunds, roughly how many steps is that? The history is already sorted: what does that let you skip?

## Your task

Make the tests pass: the same prices, for 5,000 refunds over 100,000 price changes (Java: 50,000 over 1,000,000) in under one second.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out PricesTest
```

Or, from the top of the repo: `python3 practice.py check 04 --track data-structures` (add `--java` for Java).

Stuck? `python3 practice.py hint 04 --track data-structures`. The worked answer and why it works are in
[`solutions/data-structures/04-price-at-a-moment/`](../../solutions/data-structures/04-price-at-a-moment/). Try first.

## Done when

- Each refund gets the last price that took effect at or before its order time.
- You can say why a sorted list lets you halve the search at every step.

## Then

- Linear against binary search, with the steps counted: [`big_o.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/big_o.py). In SQL the same question is a point-in-time join (the SQL page of the guide).

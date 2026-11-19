# 01 · The slow duplicate check

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Data Structures &amp; Algorithms](https://enrichmeai.com/well-rounded-engineer/dsa/) in the guide

## The story

A nightly import refuses a file if any order id appears twice. With 2,000 orders it took a blink. The business grew tenfold, the files grew tenfold, and the check now takes a hundred times longer. Nobody changed the code.

## Before you start: predict

The check compares every id with every other id. If the file grows 10 times, how many times more comparisons does it make? Why 100, and not 10?

## Your task

Make the tests pass: the same answers, but on 20,000 ids (300,000 in Java) in under one second.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out DuplicatesTest
```

Or, from the top of the repo: `python3 practice.py check 01 --track data-structures` (add `--java` for Java).

Stuck? `python3 practice.py hint 01 --track data-structures`. The worked answer and why it works are in
[`solutions/data-structures/01-slow-duplicate-check/`](../../solutions/data-structures/01-slow-duplicate-check/). Try first.

## Done when

- The tests pass, and the large case takes milliseconds, not seconds.
- You can say what the Big O of your version is, and why.

## Then

- Steps counted for nested loops against a set: [`big_o.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/big_o.py) in the fundamentals repo.

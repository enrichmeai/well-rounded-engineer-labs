# 06 · The report sorted the wrong way

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide

## The story

The store managers want one list: leave out cancelled sales, then stores A to Z, and within each store the biggest sale first. The report comes out in order of sale size only, with stores mixed together.

## Before you start: predict

The code sorts by store, then sorts again by total. What does the second sort do to the order the first one made? If the sort is stable, which order should you sort in?

## Your task

Make the tests pass: filter, map and sort by two keys, store ascending then total descending.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out SalesTest
```

Or, from the top of the repo: `python3 practice.py check 06 --track functional` (add `--java` for Java).

Stuck? `python3 practice.py hint 06 --track functional`. The worked answer and why it works are in
[`solutions/functional/06-sorted-wrong-way/`](../../solutions/functional/06-sorted-wrong-way/). Try first.

## Done when

- Stores are A to Z, and within a store the biggest sale is first.
- You can name two ways to sort by two keys.

## Then

- Filter, map, sort and group, side by side in Python and Java: [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.

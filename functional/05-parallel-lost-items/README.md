# 05 · The parallel stream that lost items

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide

## The story

To speed up a batch job, someone makes the loop parallel. It is faster. It also drops a few thumbnails on most runs, puts the rest in a different order each time, and once in a while crashes. On a laptop with two cores it looked fine.

## Before you start: predict

Several threads call `add` on the same `ArrayList` (Python: append to one list as each task finishes). Is that list safe to change from several threads at once? And even if it were, in what order would the results land?

## Your task

Make the tests pass: every id gets its thumbnail, in the same order as the ids, still computed in parallel.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out ThumbnailsTest
```

Or, from the top of the repo: `python3 practice.py check 05 --track functional` (add `--java` for Java).

Stuck? `python3 practice.py hint 05 --track functional`. The worked answer and why it works are in
[`solutions/functional/05-parallel-lost-items/`](../../solutions/functional/05-parallel-lost-items/). Try first.

## Done when

- All results are there, in input order, every run.
- You can say why `map(...).toList()` is safe in parallel and `forEach(list::add)` is not.

## Then

- When a parallel stream is worth it, measured, and what ordering costs: [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.

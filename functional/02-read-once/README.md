# 02 · The pipeline you can only read once

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide

## The story

The daily report shows the right number of orders and £0 revenue. In Java the same report crashes with "stream has already been operated upon or closed".

## Before you start: predict

`order_totals` returns a generator (Java: a `Stream`). The report reads it once to count and again to add up. What is left in it the second time?

## Your task

Make the tests pass: the count and the revenue both right, from one pass over the data or from one stored list.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out ReportTest
```

Or, from the top of the repo: `python3 practice.py check 02 --track functional` (add `--java` for Java).

Stuck? `python3 practice.py hint 02 --track functional`. The worked answer and why it works are in
[`solutions/functional/02-read-once/`](../../solutions/functional/02-read-once/). Try first.

## Done when

- Count and revenue are both right.
- You can say what "lazy" means for a generator or a stream, and what it costs.

## Then

- Lazy pipelines and when they help: [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.

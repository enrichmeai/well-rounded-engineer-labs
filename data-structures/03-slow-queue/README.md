# 03 · The slow queue

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Data Structures &amp; Algorithms](https://enrichmeai.com/well-rounded-engineer/dsa/) in the guide

## The story

A worker takes jobs off a queue, oldest first. On a quiet day it keeps up. After a backlog of a few hundred thousand jobs builds up, it falls further behind with every job, even though each job takes no time at all.

## Before you start: predict

The queue is a list, and the worker takes the first item with `pop(0)` (Java: `remove(0)`). What has to happen to every other item in the list each time the first one is removed?

## Your task

Make the tests pass: the same jobs, in the same order, for 300,000 jobs (500,000 in Java) in under one second.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out QueueTest
```

Or, from the top of the repo: `python3 practice.py check 03 --track data-structures` (add `--java` for Java).

Stuck? `python3 practice.py hint 03 --track data-structures`. The worked answer and why it works are in
[`solutions/data-structures/03-slow-queue/`](../../solutions/data-structures/03-slow-queue/). Try first.

## Done when

- Jobs still come out oldest first.
- You can say why removing from the front of an array list is O(n).

## Then

- Breadth-first search uses exactly this kind of queue: [`graph.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/graph.py) and [`java/Graph.java`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/java/Graph.java).

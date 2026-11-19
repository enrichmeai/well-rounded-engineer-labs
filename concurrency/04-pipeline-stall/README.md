# 04 · The pipeline that never finishes

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) in the guide

## The story

A nightly job prices the day's orders with a pool of worker threads and a pair of queues. One night it never finishes.
The dashboard shows it "running" at 9 a.m. One order had an item nobody had put a price on.

## Before you start: predict

Each worker takes orders off a queue until it sees `STOP`, and then sends `STOP` on. The collector waits for one `STOP`
from each worker. What happens to the collector if a worker crashes halfway?

## Your task

Make the tests pass: `run()` must finish, return the totals for the good orders, and **report** the order that could
not be priced, instead of hanging or hiding it.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out PipelineTest
```

## Done when

- The run finishes with one bad order in it, and that order's id is in the failed list.
- You can say why "the worker crashed" turned into "the job hangs", and not into an error.

## Then

- The working pipeline, with back-pressure from a bounded queue: [`pipeline.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/pipeline.py).

# 03 · The lock that outlived its owner

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Distributed Systems](https://enrichmeai.com/well-rounded-engineer/distsys/) in the guide

## The story

Only one worker at a time may write the nightly report, so each worker takes a lock (a lease) before writing. Worker A takes it, then freezes for a long garbage-collection pause. Its lease expires; worker B takes the lock and writes the report. Then A wakes up, still believing it holds the lock, and overwrites B's report with stale data.

## Before you start: predict

A does not know it was paused. Checking "do I still hold the lock?" just before writing does not help either. Why not? What could the **storage** check instead?

## Your task

Make the tests pass. The lock service hands out a **fencing token** with each lease, a number that goes up every time. Make the storage refuse a write whose token is older than one it has already accepted.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out FencingTest
```

Or, from the top of the repo: `python3 practice.py check 03 --track distributed-systems` (add `--java` for Java).

Stuck? `python3 practice.py hint 03 --track distributed-systems`. The worked answer and why it works are in
[`solutions/distributed-systems/03-lock-outlived-owner/`](../../solutions/distributed-systems/03-lock-outlived-owner/). Try first.

These drills need no network and no timing: each one replays the awkward moment exactly, every time.

## Done when

- A's late write is refused, and B's report stays.
- You can say why the check has to be in the storage, not in the worker.

## Then

- Leases, split brain and the generation clock (a number that goes up with each new leader) are on the [Distributed Systems](https://enrichmeai.com/well-rounded-engineer/distsys/) page of the guide.

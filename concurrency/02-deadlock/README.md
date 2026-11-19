# 02 · The deadlock

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) in the guide

## The story

Customers move money between two accounts, in both directions at once. One afternoon the transfer service stops
answering. The process is still running and uses no CPU. Nothing is in the logs.

## Before you start: predict

`transfer(a, b)` locks `a`, then `b`. At the same moment another thread calls `transfer(b, a)`. Draw the two threads
and the two locks. What is each one waiting for?

## Your task

Make the tests pass. **Keep one lock per account** (a single lock around every transfer would also pass, but it makes
every transfer in the bank wait for every other one). Keep the call to `record_audit_entry()`.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out TransfersTest
```

The test waits 5 seconds before it calls a stuck run a deadlock, so a failing run takes a few seconds.

## Done when

- 200 transfers each way finish, and the total money is unchanged.
- You can name the rule that stops a deadlock without a timeout.

## Then

- The four conditions every deadlock needs, and the fix drawn as a cycle that is broken:
  [`deadlock.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/deadlock.py).

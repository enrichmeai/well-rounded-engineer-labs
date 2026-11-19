# 01 · The double charge

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Distributed Systems](https://enrichmeai.com/well-rounded-engineer/distsys/) in the guide

## The story

A customer pays £100. The payment service charges the card, but its reply is lost on the way back. The app sees a timeout and does what apps do: it tries again. The customer is charged £200, and support has a very long day.

## Before you start: predict

The app cannot tell "my request was lost" from "the reply was lost". So it must retry. What would the payment service need to remember so that a retry does no harm?

## Your task

Make the tests pass: the same **idempotency key** sent twice charges once and returns the same receipt both times; a new key is a new charge.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out PaymentsTest
```

Or, from the top of the repo: `python3 practice.py check 01 --track distributed-systems` (add `--java` for Java).

Stuck? `python3 practice.py hint 01 --track distributed-systems`. The worked answer and why it works are in
[`solutions/distributed-systems/01-double-charge/`](../../solutions/distributed-systems/01-double-charge/). Try first.

These drills need no network and no timing: each one replays the awkward moment exactly, every time.

## Done when

- A retried request with the same key charges once.
- You can explain why the key must come from the client, not the server.

## Then

- Twenty orders over a network that loses 30% of messages, with and without keys: [`idempotency.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/distsys/idempotency.py) in the fundamentals repo.

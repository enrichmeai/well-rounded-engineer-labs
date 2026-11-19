# 05 · The update that went back in time

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Distributed Systems](https://enrichmeai.com/well-rounded-engineer/distsys/) in the guide

## The story

A customer changes their email address twice in a minute. Both changes are published as events. The profile service applies events as they arrive, and on a busy day they do not arrive in order. The profile ends up with the first, abandoned address.

## Before you start: predict

Each event carries the version it was made at: 1, 2, 3. If they arrive as 1, 3, 2, what does "apply whatever arrives" leave behind? What does the service need to compare before applying?

## Your task

Make the tests pass: whatever order the updates arrive in, and even if one arrives twice, the profile ends with the newest one.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out ProfileTest
```

Or, from the top of the repo: `python3 practice.py check 05 --track distributed-systems` (add `--java` for Java).

Stuck? `python3 practice.py hint 05 --track distributed-systems`. The worked answer and why it works are in
[`solutions/distributed-systems/05-back-in-time/`](../../solutions/distributed-systems/05-back-in-time/). Try first.

These drills need no network and no timing: each one replays the awkward moment exactly, every time.

## Done when

- All six arrival orders of three updates end with version 3's email.
- A duplicate delivery changes nothing.

## Then

- A store that lets a delayed duplicate win, found by trying 1,000 seeded schedules: [`dst.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/distsys/dst.py), and Lamport clocks for ordering events across machines: [`lamport.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/distsys/lamport.py).

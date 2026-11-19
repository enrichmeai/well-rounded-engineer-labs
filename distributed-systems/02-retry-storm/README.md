# 02 · The retry storm

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Distributed Systems](https://enrichmeai.com/well-rounded-engineer/distsys/) in the guide

## The story

The search service hiccups for a second. A thousand clients see errors and retry after exactly 100 ms, then again 100 ms later, then again. Every retry wave lands at the same instant, the service falls over again each time, and a one-second blip becomes a ten-minute outage.

## Before you start: predict

Each client waits a fixed 100 ms between attempts. If 1,000 clients fail at the same moment, how many of them retry in the same 10 ms window? What two changes would spread them out?

## Your task

Write `backoff_delays` (Java: `backoffDelays`) so that the waits **grow** (double each attempt, up to a cap) and are **randomised** (full jitter: a random wait between zero and the current ceiling). The tests use a seeded random generator, so the results repeat.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out BackoffTest
```

Or, from the top of the repo: `python3 practice.py check 02 --track distributed-systems` (add `--java` for Java).

Stuck? `python3 practice.py hint 02 --track distributed-systems`. The worked answer and why it works are in
[`solutions/distributed-systems/02-retry-storm/`](../../solutions/distributed-systems/02-retry-storm/). Try first.

These drills need no network and no timing: each one replays the awkward moment exactly, every time.

## Done when

- No more than a quarter of 1,000 clients retry in the same 10 ms.
- Later attempts wait longer, but never longer than the cap.

## Then

- Retries, timeouts and circuit breakers together: the [Microservices](https://enrichmeai.com/well-rounded-engineer/microservices/) page of the guide. Retry only operations that are safe to repeat (drill 01).

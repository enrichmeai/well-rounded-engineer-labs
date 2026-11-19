# 04 · The stale read

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Distributed Systems](https://enrichmeai.com/well-rounded-engineer/distsys/) in the guide

## The story

Customer data is kept on three replicas. During a network blip one replica misses an update to a customer's address. Afterwards some reads return the new address and some the old one, depending on which replica answers. A parcel goes to the house the customer moved out of.

## Before you start: predict

There are N = 3 replicas. A write counts as done when W of them confirm it; a read asks R of them. With W = 1 and R = 1, can a read miss the newest write? Which values of W and R make every read set overlap every write set?

## Your task

Set `W` and `R`, and fix `read`, so that every possible read sees the newest value, even with one replica cut off during the write. A write that cannot reach W replicas must be refused.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out QuorumTest
```

Or, from the top of the repo: `python3 practice.py check 04 --track distributed-systems` (add `--java` for Java).

Stuck? `python3 practice.py hint 04 --track distributed-systems`. The worked answer and why it works are in
[`solutions/distributed-systems/04-stale-read/`](../../solutions/distributed-systems/04-stale-read/). Try first.

These drills need no network and no timing: each one replays the awkward moment exactly, every time.

## Done when

- Every combination of R replicas returns the newest address.
- You can say why W + R > N is the rule, and what W = N costs.

## Then

- Every (W, R) pair for N = 3, run and reported: [`quorum.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/distsys/quorum.py) in the fundamentals repo.

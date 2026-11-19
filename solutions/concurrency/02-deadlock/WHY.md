# Why the fix works

A deadlock needs all four of these at once: only one holder per lock, holding one lock while waiting for another, no
way to take a lock away, and a **cycle** of waiting. Taking the locks in one global order, here by account id, makes a
cycle impossible: whoever gets the lower id first goes on to get the higher one, and the other thread simply waits.

**Wrong fixes people try first:**
- Retrying with a timeout. It turns a hang into slow, random failures; the cycle is still there.
- One lock for every account. It works, but every transfer in the system now queues behind every other one.
- Releasing the first lock before taking the second. The two balances can then be seen half-updated.

**Where you meet this for real:** two database transactions that update the same rows in opposite orders. Most
databases detect that cycle and kill one transaction, so the fix there is the same: update rows in a fixed order.

See the cycle drawn in the [guide](https://enrichmeai.com/well-rounded-engineer/concurrency/) and in [`deadlock.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/deadlock.py).

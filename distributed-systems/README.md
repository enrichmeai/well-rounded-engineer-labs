# Distributed systems drills

Five drills, each in Python and Java, about what goes wrong when work is spread across machines: lost replies, retries,
pauses, replicas and messages out of order. None of them needs a network or a clock: each test replays the awkward
moment exactly, so the result is the same every run.

Read first: [Distributed Systems](https://enrichmeai.com/well-rounded-engineer/distsys/) in the guide.

| Drill | You learn | Time |
|---|---|---|
| [01 · The double charge](01-double-charge/) | Idempotency keys make retries safe | 10 min |
| [02 · The retry storm](02-retry-storm/) | Exponential backoff with jitter | 15 min |
| [03 · The lock that outlived its owner](03-lock-outlived-owner/) | Fencing tokens: the storage must refuse a stale holder | 15 min |
| [04 · The stale read](04-stale-read/) | W + R > N, and keep the highest version | 15 min |
| [05 · The update that went back in time](05-back-in-time/) | Apply only what is newer: order by version, not by arrival | 10 min |

The answers and why they work are in [`../solutions/distributed-systems/`](../solutions/distributed-systems/). The working, simulated versions of
several of these are in the [fundamentals distsys folder](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/distsys).

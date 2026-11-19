## Hint 1
Two problems: every client waits the same time (so they stay in step), and the wait never grows (so a struggling
service gets no breathing room).

## Hint 2
The ceiling for attempt n (counting from 0) is `min(cap, base * 2**n)`. Full jitter picks the wait uniformly between 0
and that ceiling.

## Hint 3
Python: `[rng.uniform(0, min(cap, base * 2 ** n)) for n in range(attempts)]`.
Java: `rng.nextDouble() * Math.min(cap, base * Math.pow(2, n))` for each n.

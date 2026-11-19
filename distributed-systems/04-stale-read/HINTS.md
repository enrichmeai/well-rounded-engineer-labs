## Hint 1
With W = 1 and R = 1 a read can ask exactly the replica that missed the write. The read set and the write set must share
at least one replica.

## Hint 2
They always share one when W + R > N. For N = 3, W = 2 and R = 2 works, and survives one replica being unreachable.

## Hint 3
Overlapping is not enough on its own: the read gets several answers and must keep the one with the **highest version**,
not the first one. Python: `max(asked, key=lambda r: r.version).value`.

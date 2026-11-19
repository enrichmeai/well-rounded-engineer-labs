## Hint 1
The storage accepts every write. It has no way to tell that A's lease ended long ago.

## Hint 2
Every write carries the token of the lease it was made under, and tokens only go up. If the storage remembers the
highest token it has accepted, a write with a lower one must come from an expired lease.

## Hint 3
Keep `self.highest_token`. In `write`, if `token < self.highest_token`, return False without writing; otherwise
update `highest_token`, write, and return True. An equal token is the same holder writing again, which is fine.

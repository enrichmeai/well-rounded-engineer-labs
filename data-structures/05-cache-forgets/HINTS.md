## Hint 1
The cache keeps entries in the order they were added, and nothing changes that order when an entry is read.

## Hint 2
Every read or update should move the entry to the "most recent" end, so the entry at the other end is the one nobody
has used for longest.

## Hint 3
Python: `self.data.move_to_end(key)` in `get` (when the key is there) and in `put` (when it already exists).
Java: one argument. `new LinkedHashMap<>(16, 0.75f, true)`: the `true` means access order, not insertion order.

## Hint 1
Two threads can both read the same balance before either writes. Then the second write replaces the first.

## Hint 2
The read and the write must happen as one step that only one thread can be inside at a time. Python's
`threading.Lock` and Java's `synchronized` both give you that.

## Hint 3
Python: create one `threading.Lock()` in `__init__`, and put the whole body of `deposit` inside `with self._lock:`.
Java: mark `deposit` as `synchronized`. Locking only the write is not enough: the stale value was read before it.

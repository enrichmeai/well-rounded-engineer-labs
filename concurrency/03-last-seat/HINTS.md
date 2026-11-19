## Hint 1
All 40 threads can see `seats_left == 10` before any of them takes a seat. The check was true when they looked; it is
not true by the time they act.

## Hint 2
The check and the act must be one step. Put the whole of `book()`, check included, under one lock.

## Hint 3
Locking only the decrement does not help: 40 threads have already passed the check. Python: `with self._lock:` around
the whole body. Java: `synchronized boolean book(...)`.

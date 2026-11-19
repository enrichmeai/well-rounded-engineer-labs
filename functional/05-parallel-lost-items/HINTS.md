## Hint 1
Java: `ArrayList` is not thread-safe, so concurrent `add`s lose items or throw. Python: each result is appended when its
task finishes, so the list is in finishing order, not input order.

## Hint 2
Do not collect by changing a shared list. Let the library collect: it keeps each result in its place.

## Hint 3
Java: `ids.parallelStream().map(Thumbnails::resize).toList()` keeps encounter order.
Python: `list(pool.map(resize, ids))` returns results in the order of `ids`.

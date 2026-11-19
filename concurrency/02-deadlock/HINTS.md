## Hint 1
Thread 1 holds A's lock and wants B's. Thread 2 holds B's lock and wants A's. Each waits for the other for ever.

## Hint 2
A deadlock needs a cycle of waiting. If every thread takes the locks in the **same order**, no cycle can form.

## Hint 3
Each account has an `id`. Lock the account with the smaller id first, whichever way the money is going:
`first, second = sorted((source, target), key=lambda a: a.id)` in Python; compare `id`s in Java.

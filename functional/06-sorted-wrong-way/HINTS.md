## Hint 1
The second sort reorders everything by total. The store order from the first sort only survives among equal totals.

## Hint 2
Either sort once by a combined key (store, then total descending), or, because the sort is stable, sort by the
**secondary** key first and the **primary** key last.

## Hint 3
Python: `sorted(rows, key=lambda r: (r[0], -r[1]))`. Java:
`Comparator.comparing(Row::store).thenComparing(Row::total, Comparator.reverseOrder())`.

## Hint 1
A generator (and a Java `Stream`) produces its values once, as they are asked for. After the first full read, it is empty.

## Hint 2
Either store the values once (a list), or compute both answers in a single pass.

## Hint 3
Python: `totals = list(order_totals(orders))`. Java: `IntSummaryStatistics s = …mapToInt(…).summaryStatistics()`, then
`s.getCount()` and `s.getSum()`; or `.toList()` once and stream the list twice.

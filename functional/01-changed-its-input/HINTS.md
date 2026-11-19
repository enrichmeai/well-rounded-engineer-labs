## Hint 1
Both functions change the list they were given (`prices[i] = …`, `prices.sort()`), and that list belongs to the caller.

## Hint 2
Build and return a new list instead. The input is read, never written.

## Hint 3
Python: `[round(p * (1 + rate), 2) for p in prices]` and `sorted(prices)[:3]`.
Java: `prices.stream().map(p -> …).toList()` and `prices.stream().sorted().limit(3).toList()`.
